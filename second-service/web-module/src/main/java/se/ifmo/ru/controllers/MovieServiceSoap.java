package se.ifmo.ru.controllers;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface MovieServiceSoap {
    
    @WebMethod
    int addMoviesOscar();

    @WebMethod
    int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount);

}
