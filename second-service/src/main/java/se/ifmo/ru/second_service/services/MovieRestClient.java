package se.ifmo.ru.second_service.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.second_service.models.Movie;

@ApplicationScoped
public class MovieRestClient {
    
    private Client client;
    private final String serviceUrl = "https://localhost:8443/first-service-0.0.1-SNAPSHOT";

    public Response addMoviesOscar() {
        String url = serviceUrl + "/movies/reward-r";
        try {
            client = createClientWithTrustStore();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            client.close();

            return response;

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Response awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        String url = serviceUrl + "/movies/honor-by-length/" + minLength + "/oscars-to-add";
        try {
            client = createClientWithTrustStore();

            Response response = client.target(url).queryParam("oscarsCount", oscarsCount).queryParam("minLength", minLength).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(null, MediaType.APPLICATION_JSON));

            client.close();

            return response;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private static Client createClientWithTrustStore() {
        String keystorePath = "keystore.p12";
        String keystorePassword = "password";

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");

            InputStream keyStoreFileInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(keystorePath);
            keyStore.load(keyStoreFileInputStream, keystorePassword.toCharArray());
            
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

            return ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Ошибка настройки SSL клиента", e);
        }
    }
}
