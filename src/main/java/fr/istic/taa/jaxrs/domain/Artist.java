package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.ArtistDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Artist implements Serializable {

    private Long id;

    protected String firstname;
    protected String lastname;

    private List<Passage> passages = new ArrayList<Passage>();

    public Artist() {
        super();
    }

    public Artist(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

        dto.setPassagesIds(this.passages.stream().map(Passage::getId).collect(Collectors.toList()));
        return dto;
    }
}
