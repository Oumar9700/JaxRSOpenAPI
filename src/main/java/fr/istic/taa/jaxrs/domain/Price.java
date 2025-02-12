package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Price {

    private Long id;

    private double price;
    private String type; //VIP, PREMIUM, NORMAL
    private String description;

    private Concert concert;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Price() {
        super();
    }

    public Price(double price, String type, String description, Concert concert) {
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
}
