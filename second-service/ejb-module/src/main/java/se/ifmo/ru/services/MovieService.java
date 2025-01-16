package se.ifmo.ru.services;

import jakarta.ejb.Remote;
import jakarta.ws.rs.core.Response;

@Remote
public interface MovieService {
    // Response addMoviesOscar();
    String addMoviesOscar();

    Response awardMoviesByOscarsAndDuration(int minLength, long oscarsCount);

}
