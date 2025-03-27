package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.Place;
import fr.istic.taa.jaxrs.domain.Price;
import jakarta.persistence.*;

import java.io.Serializable;

public class TicketDto implements Serializable {

    private Long id;

    private boolean status;
    private Price price;
    private Client client;
    private Place place;

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
        return price.getId()    ;
    }

    public void setPriceId(Long priceId) {
        this.price.setId(priceId);
    }

    public Long getClientId() {
        return client.getId();
    }

    public void setClientId(Long clientId) {
        this.client.setId(clientId);
    }

    public Long getPlaceId() {
        return place.getId()    ;
    }

    public void setPlaceId(Long placeId) {
        this.place.setId(placeId);
    }
}
