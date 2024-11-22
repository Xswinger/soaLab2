package se.ifmo.ru.second_service.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
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
    private final String serviceUrl = "https://localhost:8080/first-service-0.0.1-SNAPSHOT";

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

    public List<Movie> awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        String url = serviceUrl + "/movies/honor-by-length/" + minLength + "/oscars-to-add";
        try {
            client = createClientWithTrustStore();

            Response response = client.target(url).queryParam("oscarsCount", oscarsCount).queryParam("minLength", minLength).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(null, MediaType.APPLICATION_JSON));

            client.close();

            return response.readEntity(new GenericType<List<Movie>>() {});
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private static Client createClientWithTrustStore() {
        try {
        System.out.println("Загрузка truststore...");
        InputStream trustStoreStream = MovieRestClient.class
                .getClassLoader()
                .getResourceAsStream("truststore.jks");

        if (trustStoreStream == null) {
            throw new RuntimeException("Файл truststore.jks не найден в classpath");
        }

        KeyStore truststore = KeyStore.getInstance("JKS");
        truststore.load(trustStoreStream, "password".toCharArray());
        System.out.println("Truststore успешно загружен!");

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(truststore);
        System.out.println("TrustManager настроен!");

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);
        System.out.println("SSLContext успешно настроен!");

        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true; // Игнорирует проверку имени
                }
            };

        return ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .hostnameVerifier(hostnameVerifier)
                .build();
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Ошибка настройки SSL клиента", e);
    }
    }
}
