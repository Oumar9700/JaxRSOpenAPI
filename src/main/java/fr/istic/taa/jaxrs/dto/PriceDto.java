package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Concert;
import fr.istic.taa.jaxrs.domain.Ticket;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PriceDto implements Serializable {

    private Long id;

    private double price;
    private String type; //VIP, PREMIUM, NORMAL
    private String description;

    private Long concertId;
    private List<Long> ticketsIds = new ArrayList<Long>();

    public PriceDto() {
        super();
    }

    public PriceDto(double price, String type, String description, Long concertId) {
        this.price = price;
        this.type = type;
        this.description = description;
        this.concertId = concertId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public List<Long> getTicketsIds() {
        return ticketsIds;
    }

    public void setTicketsIds(List<Long> ticketsIds) {
        this.ticketsIds = ticketsIds;
    }
}
