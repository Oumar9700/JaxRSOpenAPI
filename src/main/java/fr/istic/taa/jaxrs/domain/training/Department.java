package fr.istic.taa.jaxrs.domain.training;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import fr.istic.taa.jaxrs.dto.training.DepartmentDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Department implements Serializable {

    private Long id;

    private String name;

    private List<Employee> employees = new ArrayList<Employee>();

    public Department() {
        super();
    }

    public Department(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    //Transform Department Object to DepartmentDto
    public DepartmentDto toDto(){

        DepartmentDto dto = new DepartmentDto();
        dto.setId(this.getId());
        dto.setName(this.getName());
        dto.setEmployeesIds(this.employees.stream().map(Employee::getId).collect(Collectors.toList()));
        return dto;
    }
}
