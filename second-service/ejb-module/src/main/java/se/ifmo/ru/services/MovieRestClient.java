package se.ifmo.ru.services;

import javax.net.ssl.SSLContext;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class MovieRestClient {
    private final String serviceUrl = "https://10.5.0.1:4500/first-service-0.0.1-SNAPSHOT";

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

            return response;
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            return Response.status(500).build();
        }
    }
}
