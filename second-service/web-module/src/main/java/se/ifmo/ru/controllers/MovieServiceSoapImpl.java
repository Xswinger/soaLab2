package se.ifmo.ru.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.jws.WebService;
import se.ifmo.ru.services.RemoteService;

@ApplicationScoped
@WebService(endpointInterface = "se.ifmo.ru.controllers.MovieServiceSoap")
public class MovieServiceSoapImpl implements MovieServiceSoap {

    @Inject
    private RemoteService service;

    public MovieServiceSoapImpl() {}

    @Override
    public int addMoviesOscar() {
        Integer status = this.service.addMoviesOscar();
        return status;
    }

    @Override
    public int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        Integer status = this.service.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
        return status;
    }
    
}
