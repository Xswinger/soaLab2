package se.ifmo.ru.second_service.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.ifmo.ru.second_service.models.Movie;

@Stateless
public class MovieRestClient {
    
    private Client client;
    private final String serviceUrl = "https://localhost:4500";

    public Object addMoviesOscar() {
        String url = serviceUrl + "/movies/reward-r";
        try {
            client = ClientBuilder.newClient();

            Response response = client.target(url).request(MediaType.APPLICATION_JSON_TYPE).get();

            client.close();

            return response.getEntity();

        } catch(Exception ex) {
            return null;
        }
    }

    public List<Movie> awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        String url = serviceUrl + "/movies/honor-by-length/" + minLength + "/oscars-to-add";
        try {
            client = ClientBuilder.newClient();

            Response response = client.target(url).queryParam("oscarsCount", oscarsCount).queryParam("minLength", minLength).request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(null, MediaType.APPLICATION_JSON));

            client.close();

            return response.readEntity(new GenericType<List<Movie>>() {});
        } catch(Exception ex) {
            return null;
        }
    }
}
