package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
