package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.Place;
import fr.istic.taa.jaxrs.domain.Price;
import jakarta.persistence.*;

import java.io.Serializable;

public class TicketDto implements Serializable {

    private Long id;

    private boolean status;
    private Long priceId;
    private Long clientId;
    private Long placeId;

    public TicketDto() {
        super();
    }

    public TicketDto(boolean status) {
        this.status = status;
    }

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

    public Long getPriceId() {
        return priceId    ;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {

        this.clientId = clientId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {

        this.placeId = placeId;
    }
}
