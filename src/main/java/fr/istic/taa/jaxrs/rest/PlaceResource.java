package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.dao.generic.PlaceDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.domain.Place;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.PlaceDto;
import fr.istic.taa.jaxrs.dto.PlaceDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("place")
@Produces({"application/json"})
public class PlaceResource {

  PlaceDao placeDao = new PlaceDao();

  @GET
  @Path("/{placeId}")
  public PlaceDto getPlaceById(@PathParam("placeId") Long placeId)  {
    Place place =  placeDao.findOne(placeId);
    return place.toDto();
  }

  @GET
  @Path("/")
  public List<PlaceDto> getPlaces()  {
    List<Place> places =  placeDao.findAll();

    return places.stream().map(Place::toDto).collect(Collectors.toList());
  }

  
  @POST
  @Consumes("application/json")
  public Response addPlace(
      @Parameter(description = "Place object that needs to be added to the store", required = true) PlaceDto placeDto) {

    ConcertDao concertDao = new ConcertDao();

    Place place = new Place();
    place.setPlaceStatus(placeDto.getPlaceStatus());
    place.setNumber(placeDto.getNumber());
    place.setConcert(concertDao.findOne(placeDto.getConcertId()));

    placeDao.save(place);

    return Response.status(Response.Status.CREATED).entity("Place created successfully").build();

    //return Response.ok().entity("SUCCESS").build();
  }


  @PUT
  @Path("/{placeId}")
  public Response updatePlace(@PathParam("placeId") Long placeId, PlaceDto placeDto) {

    Place place = placeDao.findOne(placeId);
    if (place == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Place not found").build();
    }

    ConcertDao concertDao = new ConcertDao();
    TicketDao ticketDao = new TicketDao();

    place.setPlaceStatus(placeDto.getPlaceStatus());
    place.setNumber(placeDto.getNumber());
    place.setConcert(concertDao.findOne(placeDto.getConcertId()));
    if (placeDto.getTicketId() != null) {
      place.setTicket(ticketDao.findOne(placeDto.getTicketId()));
    }
    placeDao.update(place);

    return Response.ok("Place updated successfully").build();
  }

  @DELETE
  @Path("/{placeId}")
  public Response deletePlace(@PathParam("placeId") Long placeId) {
    Place place = placeDao.findOne(placeId);
    if (place == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Place not found").build();
    }
    placeDao.delete(place);
    return Response.ok("Place deleted successfully").build();
  }
}