package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.PriceDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Price implements Serializable {

    private Long id;

    private double price;
    @Enumerated(EnumType.STRING)
    private PriceType type; //VIP, PREMIUM, NORMAL
    private String description;

    private Concert concert;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Price() {
        super();
    }

    public Price(double price, PriceType type, String description, Concert concert) {
        this.price = price;
        this.type = type;
        this.description = description;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PriceType getType() {
        return type;
    }

    public void setType(PriceType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    @OneToMany(mappedBy = "price")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    //Transform Price Object to PriceDto
    public PriceDto toDto(){

        PriceDto dto = new PriceDto();
        dto.setId(this.getId());
        dto.setPrice(this.getPrice());
        dto.setType(this.getType());
        dto.setDescription(this.getDescription());
        dto.setConcertId(this.getConcert().getId());
        dto.setTicketsIds(this.tickets.stream().map(Ticket::getId).collect(Collectors.toList()));
        return dto;
    }
}
