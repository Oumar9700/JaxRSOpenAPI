package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Gender;
import fr.istic.taa.jaxrs.domain.User;

import java.io.Serializable;

public class AdminDto extends User implements Serializable {

    public AdminDto() {
        super();
    }

    public AdminDto(String firstname, String lastname, String email, Gender gender, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

}
