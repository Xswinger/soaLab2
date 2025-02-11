package se.ifmo.ru.rest_layer.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebServiceClient;

import se.ifmo.ru.rest_layer.ws.MovieServiceSoap;

@WebServiceClient
public class MovieClient {

    private String wsdlUrl = "http://10.5.0.7:8081/web-module-1.0-SNAPSHOT/MovieServiceSoapImpl?wsdl";
    private String nsUri = "http://controllers.ru.ifmo.se/";
    private String localPart = "MovieServiceSoapImplService";

    public int awardMoviesByOscarsAndDuration(int minLength, long oscarsCount) {
        return getPort().awardMoviesByOscarsAndDuration(minLength, oscarsCount);
    }

    public int awardMoviesByRating() {
        return getPort().addMoviesOscar();
    }   

    public MovieServiceSoap getPort() {
        URL url = null;
        try {
            url = new URL(wsdlUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        QName qname = new QName(nsUri, localPart);

        Service service = Service.create(url, qname);

        return service.getPort(MovieServiceSoap.class);
    }
}
