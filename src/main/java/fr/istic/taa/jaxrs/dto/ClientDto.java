package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClientDto extends User implements Serializable {

    private List<Long> tickets = new ArrayList<Long>();

    public ClientDto() {
        super();
    }

    public List<Long> getTicketsIds() {
        return tickets;
    }

    public void setTicketsIds(List<Long> tickets) {
        this.tickets = tickets;
    }
}
