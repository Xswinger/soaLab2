package se.ifmo.ru.second_service.controllers;


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
        return Response.ok().entity(this.service.addMoviesOscar().getEntity()).build();
    }

    @POST
    @Path("/honor-by-length/{min-length}/oscars-to-add")
    public Response awardMoviesByOscarsAndDuration(@PathParam("min-length") int minLength, @QueryParam("oscarsCount") long oscarsCount) {
        return Response.ok().entity(this.service.awardMoviesByOscarsAndDuration(minLength, oscarsCount)).build();
    }

}
