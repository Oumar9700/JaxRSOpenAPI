package fr.istic.taa.jaxrs.dto;

import java.io.Serializable;

public class TicketClientDto implements Serializable {

    private Long id;

    private Long priceId;
    private String clientFirstname;
    private String clientLastname;
    private String clientEmail;
    private Long concertId;

    public TicketClientDto() {
        super();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Long getPriceId() {
        return priceId    ;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getClientFirstname() {
        return clientFirstname;
    }

    public void setClientFirstname(String clientFirstname) {

        this.clientFirstname = clientFirstname;
    }


    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public String getClientLastname() {
        return clientLastname;
    }

    public void setClientLastname(String clientLastname) {
        this.clientLastname = clientLastname;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
