package fr.istic.taa.jaxrs.rest.training;

import fr.istic.taa.jaxrs.dao.generic.training.DepartmentDao;
import fr.istic.taa.jaxrs.domain.training.Department;
import fr.istic.taa.jaxrs.dto.training.DepartmentDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("department")
@Produces({"application/json"})
public class DepartmentResource {

    DepartmentDao departmentDao = new DepartmentDao();

    @GET
    @Path("/{departmentId}")
    public DepartmentDto getDepartmentById(@PathParam("departmentId") Long departmentId)  {
        Department department =  departmentDao.findOne(departmentId);
        return department.toDto();
    }

    @GET
    @Path("/")
    public List<DepartmentDto> getDepartment()  {
        List<Department> departments =  departmentDao.findAll();
        return departments.stream().map(Department::toDto).collect(Collectors.toList());

    }

    @POST
    @Consumes("application/json")
    public Response addDepartment(
            @Parameter(name = "Department name", required = true)
            DepartmentDto departmentDto) {
        Department department = new Department(departmentDto.getName());
        departmentDao.save(department);
        return Response.status(Response.Status.CREATED).entity("Department created successfully").build();
    }

    @PUT
    @Path("/{departmentId}")
    public Response updateDepartment(@PathParam("departmentId") Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentDao.findOne(departmentId);
        if (department == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
        department.setName(departmentDto.getName());
        departmentDao.update(department);
        return Response.ok("Department updated successfully").build();
    }

    @DELETE
    @Path("/{departmentId}")
    public Response deleteDepartment(@PathParam("departmentId") Long departmentId) {
        Department department = departmentDao.findOne(departmentId);
        if (department == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Department not found").build();
        }
        departmentDao.delete(department);
        return Response.ok("Department deleted successfully").build();
    }
}