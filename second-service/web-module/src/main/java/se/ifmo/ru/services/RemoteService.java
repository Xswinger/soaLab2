package se.ifmo.ru.services;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RemoteService {

    private final MovieService movieRemoteService = getFromEJBPool("ejb:/ejb-module-1.0-SNAPSHOT/MovieServiceImpl!se.ifmo.ru.services.MovieService");

    public RemoteService() throws NamingException {}

    public int addMoviesOscar() {
        return this.movieRemoteService.addMoviesOscar();
    }

    public int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.movieRemoteService.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }

    private MovieService getFromEJBPool(String name) throws NamingException {
        InitialContext context = new InitialContext();
        return (MovieService) context.lookup(name);
    }
}
