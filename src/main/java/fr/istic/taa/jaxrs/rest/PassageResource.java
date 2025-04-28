package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ArtistDao;
import fr.istic.taa.jaxrs.dao.generic.ConcertDao;
import fr.istic.taa.jaxrs.dao.generic.PassageDao;
import fr.istic.taa.jaxrs.domain.Artist;
import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Passage;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.PassageDto;
import fr.istic.taa.jaxrs.dto.PassageDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("passage")
@Produces({"application/json"})
public class PassageResource {

  PassageDao passageDao = new PassageDao();

  @GET
  @Path("/{passageId}")
  public PassageDto getPassageById(@PathParam("passageId") Long passageId)  {
    Passage passage =  passageDao.findOne(passageId);
    return passage.toDto();
  }

  @GET
  @Path("/")
  public List<PassageDto> getPassages()  {
    List<Passage> passages =  passageDao.findAll();
    return passages.stream().map(Passage::toDto).collect(Collectors.toList());
  }


  @POST
  public Response addPassages(List<PassageDto> passagesDtos) {
    PassageDao passageDao = new PassageDao();
    ConcertDao concertDao = new ConcertDao();
    ArtistDao artistDao = new ArtistDao();

    try {
      for (PassageDto dto : passagesDtos) {
        Concert concert = concertDao.findOne(dto.getConcertId());
        if (concert == null) {
          return Response.status(Response.Status.BAD_REQUEST).entity("Concert introuvable pour ID: " + dto.getConcertId()).build();
        }

        Artist artist = artistDao.findOne(dto.getArtistId());
        if (artist == null) {
          return Response.status(Response.Status.BAD_REQUEST).entity("Artiste introuvable pour ID: " + dto.getArtistId()).build();
        }

        Passage passage = new Passage();
        passage.setBeginHour(dto.getBeginHour());
        passage.setEndHour(dto.getEndHour());
        passage.setConcert(concert);
        passage.setArtist(artist);

        passageDao.save(passage);
      }

      return Response.status(Response.Status.CREATED).entity("Passages créés avec succès").build();
    } catch (Exception e) {
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erreur lors de la création des passages").build();
    }
  }


  @PUT
  @Path("/{passageId}")
  public Response updatePassage(@PathParam("passageId") Long passageId, PassageDto passagedto) {

    Passage passage = passageDao.findOne(passageId);
    if (passage == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Passage not found").build();
    }

    passage.setBeginHour(passagedto.getBeginHour());
    passage.setEndHour(passagedto.getEndHour());

    passageDao.update(passage);

    return Response.ok("Passage updated successfully").build();
  }

  @DELETE
  @Path("/{passageId}")
  public Response deletePassage(@PathParam("passageId") Long passageId) {
    Passage passage = passageDao.findOne(passageId);
    if (passage == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Passage not found").build();
    }
    passageDao.delete(passage);
    return Response.ok("Passage deleted successfully").build();
  }
}