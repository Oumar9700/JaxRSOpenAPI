package fr.istic.taa.jaxrs.dto.training;

import fr.istic.taa.jaxrs.domain.training.Department;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

public class EmployeeDto implements Serializable {
    private Long id;

    private String name;

    private Department department;

    public EmployeeDto() {
    }

    public EmployeeDto(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public EmployeeDto(String name) {
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

    @ManyToOne
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department="
                + department.getName() + "]";
    }

}
