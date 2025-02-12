package Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends User {

    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Client() {
        super();
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
