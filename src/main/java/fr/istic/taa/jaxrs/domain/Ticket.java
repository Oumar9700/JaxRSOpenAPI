package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

@Entity
public class Ticket {

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
}
