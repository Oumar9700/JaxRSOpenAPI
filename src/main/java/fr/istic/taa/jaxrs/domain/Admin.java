package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Admin extends User implements Serializable {

    public Admin() {
        super();
    }

    public Admin(String firstname, String lastname, String email, Gender gender, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

}
