package se.ifmo.ru.controllers;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.ifmo.ru.services.RemoteService;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieController {

    @Inject
    private RemoteService service;
    
    @GET
    @Path("/reward-r")
    public Response addMoviesOscar() {
        Integer status = this.service.addMoviesOscar();
        return Response.status(status).build();
    }

    @POST
    @Path("/honor-by-length/{min-length}/oscars-to-add")
    public Response awardMoviesByOscarsAndDuration(@PathParam("min-length") int minLength, @QueryParam("oscarsCount") long oscarsCount) {
        Integer status = this.service.awardMoviesByOscarsAndDuration(minLength, oscarsCount);
        return Response.status(status).build();
    }
}
