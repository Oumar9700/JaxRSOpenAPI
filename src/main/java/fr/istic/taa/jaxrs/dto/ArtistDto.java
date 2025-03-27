package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Passage;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ArtistDto extends User implements Serializable {

    private List<Long> passages = new ArrayList<Long>();

    public ArtistDto() {
        super();
    }

    public ArtistDto(String firstname, String lastname, String email, String sexe, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    public List<Long> getPassagesIds() {
        return passages;
    }

    public void setPassagesIds(List<Long> passages) {
        this.passages = passages;
    }
}
