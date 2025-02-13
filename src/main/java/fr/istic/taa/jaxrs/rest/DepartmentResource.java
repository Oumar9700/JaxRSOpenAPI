package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.generic.DepartmentDao;
import fr.istic.taa.jaxrs.dao.generic.EmployeeDao;
import fr.istic.taa.jaxrs.domain.Department;
import fr.istic.taa.jaxrs.domain.Employee;
import fr.istic.taa.jaxrs.dto.DepartmentDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("department")
@Produces({"application/json"})
public class DepartmentResource {

    @GET
    @Path("/{departmentId}")
    public DepartmentDto getDepartmentById(@PathParam("departmentId") Long departmentId)  {
        // return department
        DepartmentDao departmentDao = new DepartmentDao();
        Department department =  departmentDao.findOne(departmentId);

        return department.toDto();
    }

    @GET
    @Path("/")
    public Department getDepartment(Long departmentId)  {
        return new Department();
    }


    @POST
    @Consumes("application/json")
    public Response addDepartment(
            @Parameter(name = "Department name", required = true) String name) {
        // add employee
        DepartmentDao departmentDao = new DepartmentDao();
        departmentDao.save(new Department(name));

        return Response.ok().entity("SUCCESS").build();
    }
}