package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ClientDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.ClientDto;
import fr.istic.taa.jaxrs.dto.training.DepartmentDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("client")
@Produces({"application/json"})
public class ClientResource {

  ClientDao clientDao = new ClientDao();

  @GET
  @Path("/{clientId}")
  public User getUserById(@PathParam("clientId") Long clientId)  {
    Client client =  clientDao.findOne(clientId);
    return client.toDto();
  }

  @GET
  @Path("/")
  public List<ClientDto> getUser()  {
    List<Client> clients =  clientDao.findAll();
    return clients.stream().map(Client::toDto).collect(Collectors.toList());
  }


  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addUser(
          @Parameter(description = "User object that needs to be added to the store", required = true)
          ClientDto clientDto) {

    // Vérifier si un client avec cet email existe déjà
    UserDao userDao = new UserDao();
    User existingUser = userDao.findByEmail(clientDto.getEmail());
    if (existingUser != null) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("message", "Email already in use");
      return Response.status(Response.Status.CONFLICT)
              .entity(errorResponse)
              .type(MediaType.APPLICATION_JSON)
              .build();
    }

    // Transformation DTO → Entity
    Client client = new Client();
    client.setFirstname(clientDto.getFirstname());
    client.setLastname(clientDto.getLastname());
    client.setEmail(clientDto.getEmail());
    client.setPhone(clientDto.getPhone());
    client.setGender(clientDto.getGender());
    client.setPassword(clientDto.getPassword());

    // Persistance
    clientDao.save(client);

    // Transformation vers DTO à renvoyer (sans mot de passe)
    ClientDto responseDto = new ClientDto();
    responseDto.setId(client.getId());
    responseDto.setFirstname(client.getFirstname());
    responseDto.setLastname(client.getLastname());
    responseDto.setEmail(client.getEmail());
    responseDto.setPhone(client.getPhone());
    responseDto.setGender(client.getGender());

    // Réponse JSON
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("message", "Client created successfully");
    responseBody.put("data", responseDto);

    return Response.status(Response.Status.CREATED)
            .entity(responseBody)
            .type(MediaType.APPLICATION_JSON)
            .build();
  }



  @PUT
  @Path("/{clientId}")
  public Response updateClient(@PathParam("clientId") Long clientId, ClientDto clientDto) {

    Client client = clientDao.findOne(clientId);
    if (client == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Client not found").build();
    }

    client.setFirstname(clientDto.getFirstname());
    client.setLastname(clientDto.getLastname());
    client.setEmail(clientDto.getEmail());
    client.setPhone(clientDto.getPhone());
    client.setGender(clientDto.getGender());
    clientDao.update(client);

    return Response.ok("Client updated successfully").build();
  }

  @DELETE
  @Path("/{clientId}")
  public Response deleteClient(@PathParam("clientId") Long clientId) {
    Client client = clientDao.findOne(clientId);
    if (client == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Client not found").build();
    }
    clientDao.delete(client);
    return Response.ok("Client deleted successfully").build();
  }
}