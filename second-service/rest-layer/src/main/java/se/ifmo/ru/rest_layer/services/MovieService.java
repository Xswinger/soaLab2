package se.ifmo.ru.rest_layer.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.ifmo.ru.rest_layer.client.MovieClient;

@ApplicationScoped
public class MovieService {

    @Inject
    private MovieClient client;

    public int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.client.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }

    public int awardMoviesByRating() {
        return this.client.awardMoviesByRating();
    }
}
