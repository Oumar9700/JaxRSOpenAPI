package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.OrganizerDao;
import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.OrganizerDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
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
  @Consumes("application/json")
  public Response addOrganizer(
      @Parameter(description = "User object that needs to be added to the store", required = true) OrganizerDto organizerDto) {

    Organizer organizer = new Organizer();
    organizer.setId(organizerDto.getId());
    organizer.setFirstname(organizerDto.getFirstname());
    organizer.setLastname(organizerDto.getLastname());
    organizer.setEmail(organizerDto.getEmail());
    organizer.setPhone(organizerDto.getPhone());
    organizer.setGender(organizerDto.getGender());
    organizerDao.save(organizer);

    return Response.status(Response.Status.CREATED).entity("organizer created successfully").build();

    //return Response.ok().entity("SUCCESS").build();
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