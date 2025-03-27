package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.AdminDto;
import fr.istic.taa.jaxrs.dto.TicketDto;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Ticket implements Serializable {

    private Long id;

    private boolean status;
    private Price price;
    private Client client;
    private Place place;

    public Ticket() {
        super();
    }

    public Ticket(boolean status) {
        this.status = status;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @ManyToOne
    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @ManyToOne
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToOne
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    //Transform Ticket Object to TicketDto
    public TicketDto toDto(){

        TicketDto dto = new TicketDto();
        dto.setId(this.getId());
        dto.setStatus(this.isStatus());
        dto.setClientId(this.getClient().getId());
        dto.setPlaceId(this.getPlace().getId());
        dto.setPriceId(this.getPrice().getId());
        return dto;
    }
}
