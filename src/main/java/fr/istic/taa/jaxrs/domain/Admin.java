package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.AdminDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class Admin extends User implements Serializable {

    private Long id;
    public Admin() {
        super();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Admin(String firstname, String lastname, String email, Gender gender, String phone, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
    }

    //Transform Admin Object to AdminDto
    public AdminDto toDto(){

        AdminDto dto = new AdminDto();
        dto.setId(this.getId());
        dto.setFirstname(this.getFirstname());
        dto.setLastname(this.getLastname());
        dto.setEmail(this.getEmail());
        dto.setGender(this.getGender());
        dto.setPhone(this.getPhone());
        return dto;
    }

}
