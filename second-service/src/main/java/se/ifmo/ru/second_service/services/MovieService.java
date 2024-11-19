package se.ifmo.ru.second_service.services;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MovieService {

    @Inject
    private MovieRestClient client;
    
    public Object addMoviesOscar() {
        return this.client.addMoviesOscar();
    }

    public Object awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.client.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }

}
