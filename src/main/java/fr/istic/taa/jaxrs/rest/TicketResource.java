package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ClientDao;
import fr.istic.taa.jaxrs.dao.generic.PlaceDao;
import fr.istic.taa.jaxrs.dao.generic.PriceDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.TicketDto;
import fr.istic.taa.jaxrs.dto.TicketDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("ticket")
@Produces({"application/json"})
public class TicketResource {

  TicketDao ticketDao = new TicketDao();
  PlaceDao placeDao = new PlaceDao();
  PriceDao priceDao = new PriceDao();
  ClientDao clientDao = new ClientDao();

  @GET
  @Path("/{ticketId}")
  public TicketDto getTicketById(@PathParam("ticketId") Long ticketId)  {
    Ticket ticket =  ticketDao.findOne(ticketId);
    return ticket.toDto();
  }

  @GET
  @Path("/")
  public List<TicketDto> getTicket()  {
    List<Ticket> tickets =  ticketDao.findAll();
    return tickets.stream().map(Ticket::toDto).collect(Collectors.toList());
  }

  
  @POST
  @Consumes("application/json")
  public Response addTicket(
      @Parameter(description = "TicketDto object that needs to be added to the store", required = true) TicketDto ticketDto) {



    Ticket ticket = new Ticket();
    ticket.setPlace(placeDao.findOne(ticketDto.getPlaceId()));
    ticket.setPrice(priceDao.findOne(ticketDto.getPriceId()));
    ticket.setClient(clientDao.findOne(ticketDto.getClientId()));
    ticket.setStatus(ticketDto.isStatus());
    ticketDao.save(ticket);

    return Response.status(Response.Status.CREATED).entity("Ticket created successfully").build();

    //return Response.ok().entity("SUCCESS").build();
  }


  @PUT
  @Path("/{ticketId}")
  public Response updateTicket(@PathParam("ticketId") Long ticketId, TicketDto ticketDto) {

    Ticket ticket = ticketDao.findOne(ticketId);
    if (ticket == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Ticket not found").build();
    }

    ticket.setPlace(placeDao.findOne(ticketDto.getPlaceId()));
    ticket.setPrice(priceDao.findOne(ticketDto.getPriceId()));
    ticket.setClient(clientDao.findOne(ticketDto.getClientId()));
    ticket.setStatus(ticketDto.isStatus());
    ticketDao.update(ticket);

    return Response.ok("Ticket updated successfully").build();
  }

  @DELETE
  @Path("/{ticketId}")
  public Response deleteTicket(@PathParam("ticketId") Long ticketId) {
    Ticket ticket = ticketDao.findOne(ticketId);
    if (ticket == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Ticket not found").build();
    }
    ticketDao.delete(ticket);
    return Response.ok("Ticket deleted successfully").build();
  }
}