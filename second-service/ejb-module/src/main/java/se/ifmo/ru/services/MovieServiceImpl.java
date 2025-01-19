package se.ifmo.ru.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieRestClient client;

    @Override
    public int addMoviesOscar() {
        return this.client.addMoviesOscar().getStatus();
    }

    @Override
    public int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.client.awardMoviesByOscarsAndDuration(minLength, oscarsCount).getStatus();
    }
}
