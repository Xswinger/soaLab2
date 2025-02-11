package se.ifmo.ru.rest_layer.ws;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(name = "MovieServiceSoap", targetNamespace = "http://controllers.ru.ifmo.se/")
public interface MovieServiceSoap {
    
    @WebMethod
    int addMoviesOscar();

    @WebMethod
    int awardMoviesByOscarsAndDuration(@WebParam(name = "arg0", partName = "arg0")int minLength, @WebParam(name = "arg1", partName = "arg1")long oscarsCount);

}
