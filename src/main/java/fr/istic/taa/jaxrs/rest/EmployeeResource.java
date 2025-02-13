package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.EmployeeDao;
import fr.istic.taa.jaxrs.domain.Employee;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("employee")
@Produces({"application/json"})
public class EmployeeResource {

    @GET
    @Path("/{employeeId}")
    public Employee getEmployeeById(@PathParam("employeeId") Long petId)  {
        // return pet
        return new Employee();
    }

    @GET
    @Path("/")
    public Employee getEmployee(Long petId)  {
        return new Employee();
    }


    @POST
    @Consumes("application/json")
    public Response addEmployee(
            @Parameter(description = "Employee object that needs to be added to the store", required = true) Employee employee) {
        // add employee
        EmployeeDao employeeDao = new EmployeeDao();
        employeeDao.save(new Employee());

        return Response.ok().entity("SUCCESS").build();
    }
}