package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("client")
@Produces({"application/json"})
public class ClientResource {

  @GET
  @Path("/{clientId}")
  public User getUserById(@PathParam("clientId") Long petId)  {
      // return pet
      return new User();
  }

  @GET
  @Path("/")
  public User getUser(Long userId)  {
      return new User();
  }

  
  @POST
  @Consumes("application/json")
  public Response addUser(
      @Parameter(description = "User object that needs to be added to the store", required = true) User user) {
      // add user

    return Response.ok().entity("SUCCESS").build();
  }
}