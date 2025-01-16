package se.ifmo.ru.services;


import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

@Stateless(mappedName = "ejb:global/myUniqueEjb/MovieServiceImpl")
public class MovieServiceImpl implements MovieService {

    @Inject
    private MovieRestClient client;
    
    // @Override
    // public Response addMoviesOscar() {
    //     return this.client.addMoviesOscar();
    // }

    @Override
    public String addMoviesOscar() {
        return "true";
    }

    @Override
    public Response awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return this.client.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }
}
