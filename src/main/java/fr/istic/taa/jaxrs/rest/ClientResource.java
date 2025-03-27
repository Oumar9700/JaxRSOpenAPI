package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ClientDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.domain.training.Department;
import fr.istic.taa.jaxrs.dto.ClientDto;
import fr.istic.taa.jaxrs.dto.training.DepartmentDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
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
  @Consumes("application/json")
  public Response addUser(
      @Parameter(description = "User object that needs to be added to the store", required = true) ClientDto clientDto) {

    Client client = new Client();
    client.setId(clientDto.getId());
    client.setFirstname(clientDto.getFirstname());
    client.setLastname(clientDto.getLastname());
    client.setEmail(clientDto.getEmail());
    client.setPhone(clientDto.getPhone());
    client.setGender(clientDto.getGender());
    clientDao.save(client);
    return Response.status(Response.Status.CREATED).entity("Client created successfully").build();

    //return Response.ok().entity("SUCCESS").build();
  }
}