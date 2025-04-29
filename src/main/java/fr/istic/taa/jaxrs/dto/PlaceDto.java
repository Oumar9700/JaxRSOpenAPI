package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.PlaceStatus;
import fr.istic.taa.jaxrs.domain.Ticket;
import jakarta.persistence.*;

import java.io.Serializable;

public class PlaceDto implements Serializable {

    private Long id;

    private Long number;
    private PlaceStatus placeStatus;
    private Long concertId;
    private Long ticketId;

    public PlaceDto() {
        super();
    }

    public PlaceDto(Long number, PlaceStatus placeStatus, Long concertId) {
        this.number = number;
        this.placeStatus = placeStatus;
        this.concertId = concertId;

    }

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

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }
}
