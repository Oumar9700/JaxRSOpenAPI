package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.AdminDto;
import fr.istic.taa.jaxrs.dto.OrganizerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Organizer extends User implements Serializable {

    private List<Concert> concerts = new ArrayList<Concert>();

    public Organizer() {
        super();
    }


    @OneToMany(mappedBy = "organizer")
    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    //Transform Admin Object to AdminDto
    public OrganizerDto toDto(){

        OrganizerDto dto = new OrganizerDto();
        dto.setId(this.getId());
        dto.setFirstname(this.getFirstname());
        dto.setLastname(this.getLastname());
        dto.setEmail(this.getEmail());
        dto.setGender(this.getGender());
        dto.setPhone(this.getPhone());
        dto.setConcertsIds(this.concerts.stream().map(Concert::getId).collect(Collectors.toList()));

        return dto;
    }
}
