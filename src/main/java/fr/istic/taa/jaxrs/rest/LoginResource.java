
package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.LoginDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;


@Path("login")
@Produces({"application/json"})
public class LoginResource {

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response login(LoginDto loginDto) {

    UserDao userDao = new UserDao();
    User user = userDao.findByEmail(loginDto.getEmail());

    System.out.println("voici user :");
    System.out.println(user);

    if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
      HashMap<Object, Object> errorResponse = new HashMap<>();
      errorResponse.put("message", "Invalid email or password");
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity(errorResponse)
              .type(MediaType.APPLICATION_JSON)
              .build();
    }

    Map<String, Object> data = new HashMap<>();
    data.put("id", user.getId());
    data.put("firstname", user.getFirstname());
    data.put("lastname", user.getLastname());
    data.put("email", user.getEmail());
    data.put("phone", user.getPhone());
    data.put("gender", user.getGender());

    if (user instanceof Client) {
      data.put("role", "client");
    } else if (user instanceof Admin) {
      data.put("role", "admin");
    }else if (user instanceof Organizer) {
      data.put("role", "organizer");
    }
    else {
      data.put("role", "user");
    }


    Map<String, Object> response = new HashMap<>();
    response.put("message", "Login successful");
    response.put("data", data);


    return Response.ok()
            .entity(response)
            .type(MediaType.APPLICATION_JSON)
            .build();
  }


}