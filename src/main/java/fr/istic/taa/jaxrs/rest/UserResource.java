package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.domain.User;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("users")
@Produces({"application/json"})
public class UserResource {

  @GET
  @Path("/")
  public User getUser(Long petId)  {
      return new User();
  }

}