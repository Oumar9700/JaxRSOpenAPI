package fr.istic.taa.jaxrs.dto.training;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDto implements Serializable {

    private Long id;

    private String name;

    private List<Long> employeesIds = new ArrayList<Long>();

    public DepartmentDto() {
        super();
    }

    public DepartmentDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getEmployees() {
        return employeesIds;
    }

    public void setEmployeesIds(List<Long> employeesIds) {
        this.employeesIds = employeesIds;
    }
}
