package se.ifmo.ru.second_service.services;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class MovieService {

    @Inject
    private MovieRestClient client;
    
    public Response addMoviesOscar() {
        return this.client.addMoviesOscar();
    }

    public Response awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.client.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }

}
