package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.AdminDao;
import fr.istic.taa.jaxrs.dao.generic.UserDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.AdminDto;
import fr.istic.taa.jaxrs.dto.ClientDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("admin")
@Produces({"application/json"})
public class AdminResource {

  AdminDao adminDao = new AdminDao();

  @GET
  @Path("/{adminId}")
  public User getAdminById(@PathParam("adminId") Long adminId)  {
    Admin admin =  adminDao.findOne(adminId);
    return admin.toDto();
  }

  @GET
  @Path("/")
  public List<AdminDto> getAdmins()  {
    List<Admin> admins =  adminDao.findAll();
    return admins.stream().map(Admin::toDto).collect(Collectors.toList());
  }

  
  @POST
  @Consumes("application/json")
  public Response addAdmin(
      @Parameter(description = "User object that needs to be added to the store", required = true) AdminDto adminDto) {

    UserDao userDao = new UserDao();
    User existingUser = userDao.findByEmail(adminDto.getEmail());
    if (existingUser != null) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("message", "Email already in use");
      return Response.status(Response.Status.CONFLICT)
              .entity(errorResponse)
              .type(MediaType.APPLICATION_JSON)
              .build();
    }

    Admin admin = new Admin();
    admin.setFirstname(adminDto.getFirstname());
    admin.setLastname(adminDto.getLastname());
    admin.setEmail(adminDto.getEmail());
    admin.setPhone(adminDto.getPhone());
    admin.setGender(adminDto.getGender());
    admin.setPassword(adminDto.getPassword());
    adminDao.save(admin);

    // Transformation vers DTO à renvoyer (sans mot de passe)
    AdminDto responseDto = new AdminDto();
    responseDto.setId(admin.getId());
    responseDto.setFirstname(admin.getFirstname());
    responseDto.setLastname(admin.getLastname());
    responseDto.setEmail(admin.getEmail());
    responseDto.setPhone(admin.getPhone());
    responseDto.setGender(admin.getGender());

    // Réponse JSON
    Map<String, Object> responseBody = new HashMap<>();
    responseBody.put("message", "Admin created successfully");
    responseBody.put("data", responseDto);

    return Response.status(Response.Status.CREATED)
            .entity(responseBody)
            .type(MediaType.APPLICATION_JSON)
            .build();

    //return Response.ok().entity("SUCCESS").build();
  }


  @PUT
  @Path("/{adminId}")
  public Response updateAdmin(@PathParam("adminId") Long adminId, AdminDto adminDto) {

    Admin admin = adminDao.findOne(adminId);
    if (admin == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Admin not found").build();
    }

    admin.setFirstname(adminDto.getFirstname());
    admin.setLastname(adminDto.getLastname());
    admin.setEmail(adminDto.getEmail());
    admin.setPhone(adminDto.getPhone());
    admin.setGender(adminDto.getGender());
    adminDao.update(admin);

    return Response.ok("Admin updated successfully").build();
  }

  @DELETE
  @Path("/{adminId}")
  public Response deleteAdmin(@PathParam("adminId") Long adminId) {
    Admin admin = adminDao.findOne(adminId);
    if (admin == null) {
      return Response.status(Response.Status.NOT_FOUND).entity("Admin not found").build();
    }
    adminDao.delete(admin);
    return Response.ok("Admin deleted successfully").build();
  }
}