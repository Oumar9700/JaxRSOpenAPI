package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.AdminDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.AdminDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
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

    Admin admin = new Admin();
    admin.setFirstname(adminDto.getFirstname());
    admin.setLastname(adminDto.getLastname());
    admin.setEmail(adminDto.getEmail());
    admin.setPhone(adminDto.getPhone());
    admin.setGender(adminDto.getGender());
    adminDao.save(admin);

    return Response.status(Response.Status.CREATED).entity("Admin created successfully").build();

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