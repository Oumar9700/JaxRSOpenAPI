package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ClientDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.ClientDto;
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

    if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
      HashMap<Object, Object> errorResponse = new HashMap<>();
      errorResponse.put("message", "Invalid email or password");
      return Response.status(Response.Status.UNAUTHORIZED)
              .entity(errorResponse)
              .type(MediaType.APPLICATION_JSON)
              .build();
    }

    // Création DTO réponse sans le mot de passe
    ClientDto responseDto = new ClientDto();
    responseDto.setId(user.getId());
    responseDto.setFirstname(user.getFirstname());
    responseDto.setLastname(user.getLastname());
    responseDto.setEmail(user.getEmail());
    responseDto.setPhone(user.getPhone());
    responseDto.setGender(user.getGender());

    Map<String, Object> response = new HashMap<>();
    response.put("message", "Login successful");
    response.put("data", responseDto);

    return Response.ok()
            .entity(response)
            .type(MediaType.APPLICATION_JSON)
            .build();
  }


}