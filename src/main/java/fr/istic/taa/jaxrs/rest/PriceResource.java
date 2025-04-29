package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.dao.generic.PriceDao;
import fr.istic.taa.jaxrs.dao.generic.TicketDao;
import fr.istic.taa.jaxrs.domain.Price;
import fr.istic.taa.jaxrs.dto.PriceDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("price")
@Produces({"application/json"})
public class PriceResource {

  PriceDao priceDao = new PriceDao();

  @GET
  @Path("/{priceId}")
  public PriceDto getPriceById(@PathParam("priceId") Long priceId)  {
    Price price =  priceDao.findOne(priceId);
    return price.toDto();
  }

  @GET
  @Path("/")
  public List<PriceDto> getPrices()  {
    List<Price> prices =  priceDao.findAll();
    return prices.stream().map(Price::toDto).collect(Collectors.toList());
  }

  
  @POST
  @Consumes("application/json")
  public Response addPrice(
      @Parameter(description = "Price object that needs to be added to the store", required = true) PriceDto priceDto) {

    ConcertDao concertDao = new ConcertDao();
    
    Price price = new Price();
    price.setPrice(priceDto.getPrice());
    price.setDescription(priceDto.getDescription());
    price.setType(priceDto.getType());
    price.setConcert(concertDao.findOne(priceDto.getConcertId()));
    
    priceDao.save(price);

    return Response.status(Response.Status.CREATED).entity("Price created successfully").build();

    //return Response.ok().entity("SUCCESS").build();
  }


  @PUT
  @Path("/{priceId}")
  public Response updatePrice(@PathParam("priceId") Long priceId, PriceDto priceDto) {

    Price price = priceDao.findOne(priceId);
    if (price == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Price not found").build();
    }

    ConcertDao concertDao = new ConcertDao();
    TicketDao ticketDao = new TicketDao();

    price.setPrice(priceDto.getPrice());
    price.setDescription(priceDto.getDescription());
    price.setType(priceDto.getType());
    price.setConcert(concertDao.findOne(priceDto.getConcertId()));


    priceDao.update(price);

    return Response.ok("Price updated successfully").build();
  }

  @DELETE
  @Path("/{priceId}")
  public Response deletePrice(@PathParam("priceId") Long priceId) {
    Price price = priceDao.findOne(priceId);
    if (price == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Price not found").build();
    }
    priceDao.delete(price);
    return Response.ok("Price deleted successfully").build();
  }
}