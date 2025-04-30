package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.ArtistDao;
import fr.istic.taa.jaxrs.domain.Artist;
import fr.istic.taa.jaxrs.dto.ArtistDto;
import fr.istic.taa.jaxrs.dto.ArtistDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("artist")
@Produces({"application/json"})
public class ArtistResource {

  ArtistDao artistDao = new ArtistDao();

  @GET
  @Path("/{artistId}")
  public ArtistDto getArtistById(@PathParam("artistId") Long artistId)  {
    Artist artist =  artistDao.findOne(artistId);
    return artist.toDto();
  }

  @GET
  @Path("/")
  public List<ArtistDto> getArtists()  {
    List<Artist> artists =  artistDao.findAll();
    return artists.stream().map(Artist::toDto).collect(Collectors.toList());
  }


  @POST
  public Response addArtists(
          @Parameter(description = "Liste d'artistes à créer", required = true)
          List<ArtistDto> artistDtos) {

    for (ArtistDto artistDto : artistDtos) {
      Artist artist = new Artist();
      artist.setFirstname(artistDto.getFirstname());
      artist.setLastname(artistDto.getLastname());

      artistDao.save(artist);
    }

    return Response.status(Response.Status.CREATED).entity("Liste d'artistes créés avec succès").build();
  }


  @PUT
  @Path("/{artistId}")
  public Response updateArtist(@PathParam("artistId") Long artistId, ArtistDto artistDto) {

    Artist artist = artistDao.findOne(artistId);
    if (artist == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Artist not found").build();
    }

    artist.setFirstname(artistDto.getFirstname());
    artist.setLastname(artistDto.getLastname());

    artistDao.update(artist);

    return Response.ok("Artist updated successfully").build();
  }

  @DELETE
  @Path("/{artistId}")
  public Response deleteArtist(@PathParam("artistId") Long artistId) {
    Artist artist = artistDao.findOne(artistId);
    if (artist == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Artist not found").build();
    }
    artistDao.delete(artist);
    return Response.ok("Artist deleted successfully").build();
  }
}