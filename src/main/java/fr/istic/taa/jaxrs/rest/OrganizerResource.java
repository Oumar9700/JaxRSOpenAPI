package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.OrganizerDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.ClientDto;
import fr.istic.taa.jaxrs.dto.OrganizerDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("organizer")
@Produces({"application/json"})
public class OrganizerResource {

  OrganizerDao organizerDao = new OrganizerDao();

  @GET
  @Path("/{organizerId}")
  public User getOrganizerById(@PathParam("organizerId") Long organizerId)  {
    Organizer organizer =  organizerDao.findOne(organizerId);
    return organizer.toDto();
  }

  @GET
  @Path("/")
  public List<OrganizerDto> getOrganizers()  {
    List<Organizer> organizers =  organizerDao.findAll();
    return organizers.stream().map(Organizer::toDto).collect(Collectors.toList());
  }

  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response addOrganizer(
      @Parameter(description = "User object that needs to be added to the store", required = true) OrganizerDto organizerDto) {

    UserDao userDao = new UserDao();
    User existingUser = userDao.findByEmail(organizerDto.getEmail());
    if (existingUser != null) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("message", "Email already in use");
      return Response.status(Response.Status.CONFLICT)
              .entity(errorResponse)
              .type(MediaType.APPLICATION_JSON)
              .build();
    }

    Organizer organizer = new Organizer();
    organizer.setFirstname(organizerDto.getFirstname());
    organizer.setLastname(organizerDto.getLastname());
    organizer.setEmail(organizerDto.getEmail());
    organizer.setPhone(organizerDto.getPhone());
    organizer.setGender(organizerDto.getGender());
    organizer.setPassword(organizerDto.getPassword());
    organizerDao.save(organizer);

    // Transformation vers DTO à renvoyer (sans mot de passe)
    OrganizerDto responseDto = new OrganizerDto();
    responseDto.setId(organizer.getId());
    responseDto.setFirstname(organizer.getFirstname());
    responseDto.setLastname(organizer.getLastname());
    responseDto.setEmail(organizer.getEmail());
    responseDto.setPhone(organizer.getPhone());
    responseDto.setGender(organizer.getGender());

    // Réponse JSON
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("message", "Organizer created successfully");
    responseBody.put("data", responseDto);

    return Response.status(Response.Status.CREATED)
            .entity(responseBody)
            .type(MediaType.APPLICATION_JSON)
            .build();
  }


  @PUT
  @Path("/{organizerId}")
  public Response updateOrganizer(@PathParam("organizerId") Long organizerId, OrganizerDto organizerDto) {

    Organizer organizer = organizerDao.findOne(organizerId);
    if (organizer == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("organizer not found").build();
    }

    organizer.setFirstname(organizerDto.getFirstname());
    organizer.setLastname(organizerDto.getLastname());
    organizer.setEmail(organizerDto.getEmail());
    organizer.setPhone(organizerDto.getPhone());
    organizer.setGender(organizerDto.getGender());
    organizerDao.update(organizer);

    return Response.ok("organizer updated successfully").build();
  }

  @DELETE
  @Path("/{organizerId}")
  public Response deleteOrganizer(@PathParam("organizerId") Long organizerId) {
    Organizer organizer = organizerDao.findOne(organizerId);
    if (organizer == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("organizer not found").build();
    }
    organizerDao.delete(organizer);
    return Response.ok("organizer deleted successfully").build();
  }
}