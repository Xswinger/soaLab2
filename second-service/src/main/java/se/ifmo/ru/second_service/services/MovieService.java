package se.ifmo.ru.second_service.services;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.second_service.models.Movie;

@ApplicationScoped
public class MovieService {

    @Inject
    private MovieRestClient client;
    
    public Response addMoviesOscar() {
        return this.client.addMoviesOscar();
    }

    public List<Movie> awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.client.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }

}
