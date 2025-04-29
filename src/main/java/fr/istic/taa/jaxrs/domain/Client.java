package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.ArtistDto;
import fr.istic.taa.jaxrs.dto.ClientDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Client extends User implements Serializable {

    private Long id;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Client() {
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

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    //Transform Client Object to ClientDto
    public ClientDto toDto(){
        ClientDto dto = new ClientDto();
        dto.setId(this.getId());
        dto.setFirstname(this.getFirstname());
        dto.setLastname(this.getLastname());
        dto.setEmail(this.getEmail());
        dto.setGender(this.getGender());
        dto.setPhone(this.getPhone());
        dto.setTicketsIds(this.tickets.stream().map(Ticket::getId).collect(Collectors.toList()));
        return dto;
    }
}
