package se.ifmo.ru.services;

import jakarta.ejb.Remote;

@Remote
public interface MovieService {
    int addMoviesOscar();

    int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount);

}
