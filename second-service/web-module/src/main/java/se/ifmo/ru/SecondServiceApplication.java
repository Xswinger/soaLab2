package se.ifmo.ru;


import javax.xml.ws.Endpoint;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import se.ifmo.ru.controllers.MovieServiceSoapImpl;

@ApplicationPath("")
public class SecondServiceApplication extends Application {

    public static void main(String[] args) {
        //! Maybe change on other ip and path
        Endpoint.publish("http://localhost:8080/soa-second-service", new MovieServiceSoapImpl());
    }

}
