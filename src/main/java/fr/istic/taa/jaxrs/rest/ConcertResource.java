package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.*;
import fr.istic.taa.jaxrs.dao.generic.configs.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.dto.ConcertDto;
import fr.istic.taa.jaxrs.dto.ConcertDto;
import fr.istic.taa.jaxrs.dto.PassageArtistDto;
import fr.istic.taa.jaxrs.dto.PriceDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;

@Path("concert")
@Produces({"application/json"})
public class ConcertResource {

  ConcertDao concertDao = new ConcertDao();

  @GET
  @Path("/{concertId}")
  public ConcertDto getConcertById(@PathParam("concertId") Long concertId)  {
    Concert concert =  concertDao.findOne(concertId);
    return concert.toDto();
  }

  @GET
  @Path("/")
  public List<ConcertDto> getConcerts()  {
    List<Concert> concerts =  concertDao.findAll();
    return concerts.stream().map(Concert::toDto).collect(Collectors.toList());
  }


  @POST
  @Consumes("application/json")
  @Produces("application/json")
  public Response addConcert(
          @Parameter(description = "Concert object with prices and places", required = true) ConcertDto concertDto) {

    // 1. Création du concert
    Concert concert = new Concert();
    concert.setTitle(concertDto.getTitle());
    concert.setDescription(concertDto.getDescription());
    concert.setCapacity(concertDto.getCapacity());
    concert.setCountry(concertDto.getCountry());
    concert.setCity(concertDto.getCity());
    concert.setAddress(concertDto.getAddress());
    concert.setBeginDate(concertDto.getBeginDate());
    concert.setEndDate(concertDto.getEndDate());
    concert.setRepaymentConditions(concertDto.getRepaymentConditions());
    concert.setValidatedConcert(concertDto.isValidatedConcert());

    // 2. Lier l'organisateur
    OrganizerDao organizerDao = new OrganizerDao();
    Organizer organizer = organizerDao.findOne(concertDto.getOrganizerId());
    if (organizer == null) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity("Organizer not found with ID: " + concertDto.getOrganizerId())
              .build();
    }
    concert.setOrganizer(organizer);

    // 3. Sauvegarde initiale du concert
    concertDao.save(concert); // Important : il faut un ID pour le concert avant de créer prices/places

    System.out.println("id de   concert :");
    System.out.println(concert.getId());
    PriceDao priceDao = new PriceDao();

    // 4. Création des prices
    if (concertDto.getPrices() != null) {
      for (PriceDto priceDto : concertDto.getPrices()) {
        Price price = new Price();
        price.setPrice(priceDto.getPrice());
        price.setType(priceDto.getType());
        price.setDescription(priceDto.getDescription());
        price.setConcert(concert);
        priceDao.save(price);
      }
    }

    ArtistDao artistDao = new ArtistDao();
    PassageDao passageDao  = new PassageDao();
    // 4. Création des prices
    if (concertDto.getPassages() != null) {
      for (PassageArtistDto passageArtistDto : concertDto.getPassages()) {

        Artist artist = new Artist();
        artist.setFirstname(passageArtistDto.getArtistFirstname());
        artist.setLastname(passageArtistDto.getArtistLastname());
        artistDao.save(artist);

        Passage passage = new Passage();
        passage.setArtist(artist);
        passage.setConcert(concert);
        passage.setBeginHour(passageArtistDto.getBeginHour());
        passage.setEndHour(passageArtistDto.getEndHour());
        passageDao.save(passage);
      }
    }

    // 5. Création des places
    PlaceDao placeDao = new PlaceDao();
    if (concertDto.getCapacity() != null && concertDto.getCapacity() > 0) {
      for (long i = 1; i <= concertDto.getCapacity(); i++) {
        Place place = new Place();
        place.setNumber(i); // numérotation simple 1,2,3...
        place.setPlaceStatus(PlaceStatus.NOT_TAKEN); // Dispo par défaut
        place.setConcert(concert);
        placeDao.save(place);
      }
    }

    // 5. Création des passages



    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("message", "Concert created successfully with prices and places");

    return Response.status(Response.Status.CREATED)
            .entity(responseBody)
            .type(MediaType.APPLICATION_JSON)
            .build();
  }



  @PUT
  @Path("/{concertId}")
  public Response updateConcert(@PathParam("concertId") Long concertId, ConcertDto concertDto) {

    Concert concert = concertDao.findOne(concertId);
    if (concert == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("concert not found").build();
    }

    concert.setTitle(concertDto.getTitle());
    concert.setDescription(concertDto.getDescription());
    concert.setCapacity(concertDto.getCapacity());
    concert.setCountry(concertDto.getCountry());
    concert.setCity(concertDto.getCity());
    concert.setAddress(concertDto.getAddress());
    concert.setBeginDate(concertDto.getBeginDate());
    concert.setEndDate(concertDto.getEndDate());
    concert.setRepaymentConditions(concertDto.getRepaymentConditions());
    concert.setValidatedConcert(concertDto.isValidatedConcert());

    // 2. Lier l'organisateur
    OrganizerDao organizerDao = new OrganizerDao();
    Organizer organizer = organizerDao.findOne(concertDto.getOrganizerId());
    if (organizer == null) {
      return Response.status(Response.Status.BAD_REQUEST)
              .entity("Organizer not found with ID: " + concertDto.getOrganizerId())
              .build();
    }
    concert.setOrganizer(organizer);

    concertDao.update(concert);

    return Response.ok("concert updated successfully").build();
  }

  @DELETE
  @Path("/{concertId}")
  public Response deleteConcert(@PathParam("concertId") Long concertId) {
    Concert concert = concertDao.findOne(concertId);
    if (concert == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("concert not found").build();
    }
    concertDao.delete(concert);
    return Response.ok("concert deleted successfully").build();
  }
}