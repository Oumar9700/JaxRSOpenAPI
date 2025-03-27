package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.AdminDto;
import fr.istic.taa.jaxrs.dto.PlaceDto;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Place implements Serializable {

    private Long id;

    private Long number;
    private PlaceStatus placeStatus;
    private Concert concert;
    private Ticket ticket;

    public Place() {
        super();
    }

    public Place(Long number, PlaceStatus placeStatus, Concert concert) {
        this.number = number;
        this.placeStatus = placeStatus;
        this.concert = concert;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public PlaceStatus getPlaceStatus() {
        return placeStatus;
    }

    public void setPlaceStatus(PlaceStatus placeStatus) {
        this.placeStatus = placeStatus;
    }

    @ManyToOne
    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    @OneToOne(mappedBy = "place")
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    //Transform Place Object to PlaceDto
    public PlaceDto toDto(){

        PlaceDto dto = new PlaceDto();
        dto.setId(this.getId());
        dto.setNumber(this.getNumber());
        dto.setPlaceStatus(this.getPlaceStatus());
        dto.setConcertId(this.getConcert().getId());
        dto.setTicketId(this.getTicket().getId());

        return dto;
    }
}
