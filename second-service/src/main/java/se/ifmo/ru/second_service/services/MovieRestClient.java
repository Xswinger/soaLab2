package se.ifmo.ru.second_service.services;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.second_service.models.Movie;

@ApplicationScoped
public class MovieRestClient {
    private final String serviceUrl = "https://first-service:8443/first-service-0.0.1-SNAPSHOT";

    public Response addMoviesOscar() {
        String url = serviceUrl + "/movies/reward-r";
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);

            Client client = ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .build();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            client.close();

            return response;

        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return Response.status(500).build();
        }
    }

    public Response awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        String url = serviceUrl + "/movies/honor-by-length/" + minLength + "/oscars-to-add";
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);

            Client client = ClientBuilder.newBuilder()
                    .sslContext(sslContext)
                    .build();

            Response response = client.target(url).queryParam("oscarsCount", oscarsCount).queryParam("minLength", minLength).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(null, MediaType.APPLICATION_JSON));

            client.close();

            return response;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return Response.status(500).build();
        }
    }
}
