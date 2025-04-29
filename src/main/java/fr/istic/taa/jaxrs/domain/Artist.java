package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.ArtistDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Artist extends User implements Serializable {

    private Long id;
    private List<Passage> passages = new ArrayList<Passage>();

    public Artist() {
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

    public Artist(String firstname, String lastname, String email, Gender gender, String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    @OneToMany(cascade = CascadeType.PERSIST)
    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }

    //Transform Artist Object to ArtistDto
    public ArtistDto toDto(){
        ArtistDto dto = new ArtistDto();
        dto.setId(this.getId());
        dto.setFirstname(this.getFirstname());
        dto.setLastname(this.getLastname());
        dto.setEmail(this.getEmail());
        dto.setGender(this.getGender());
        dto.setPhone(this.getPhone());
        dto.setPassagesIds(this.passages.stream().map(Passage::getId).collect(Collectors.toList()));
        return dto;
    }
}
