package se.ifmo.ru.second_service.controllers;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        return Response.ok().entity(this.service.addMoviesOscar()).build();
    }

    @POST
    @Path("/honor-by-length/{min-length}/oscars-to-add")
    public Response awardMoviesByOscarsAndDuration(@PathParam("min-length") int minLength, @QueryParam("oscarsCount") long oscarsCount) {
        return Response.ok().entity(this.service.awardMoviesByOscarsAndDuration(minLength, oscarsCount)).build();
    }

}
