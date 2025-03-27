package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrganizerDto extends User implements Serializable {

    private List<Long> concerts = new ArrayList<Long>();

    public OrganizerDto() {
        super();
    }

    public List<Long> getConcertsIds() {
        return concerts;
    }

    public void setConcertsIds(List<Long> concerts) {
        this.concerts = concerts;
    }
}
