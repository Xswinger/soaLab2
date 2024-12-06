package se.ifmo.ru.second_service.controllers;


import java.util.List;

import javax.net.ssl.TrustManagerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.second_service.models.Movie;
import se.ifmo.ru.second_service.services.MovieService;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    private MovieService service;
    
    @GET
    @Path("/reward-r")
    public Response addMoviesOscar() {
        Response response = this.service.addMoviesOscar();
        return Response.status(response.getStatus())
            // .header("Access-Control-Allow-Origin", "*")
            // .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
            // .header("Access-Control-Allow-Credentials", "true")
            // .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
            // .header("Access-Control-Max-Age", "1209600")
            .entity(response.getEntity()).build();
    }

    @POST
    @Path("/honor-by-length/{min-length}/oscars-to-add")
    public Response awardMoviesByOscarsAndDuration(@PathParam("min-length") int minLength, @QueryParam("oscarsCount") long oscarsCount) {
        Response response = this.service.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
        if (response.getStatus() == 200) {
            return Response.status(response.getStatus())
                // .header("Access-Control-Allow-Origin", "*")
                // .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                // .header("Access-Control-Allow-Credentials", "true")
                // .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                // .header("Access-Control-Max-Age", "1209600")
                .entity(response.readEntity(new GenericType<List<Movie>>() {})).build();    
        } else {
            return Response.status(response.getStatus())
                // .header("Access-Control-Allow-Origin", "*")
                // .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                // .header("Access-Control-Allow-Credentials", "true")
                // .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                // .header("Access-Control-Max-Age", "1209600")
                .entity(response.getEntity()).build();
        }
    }
}
