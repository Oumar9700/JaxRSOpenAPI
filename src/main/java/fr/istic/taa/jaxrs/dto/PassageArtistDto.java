package fr.istic.taa.jaxrs.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PassageArtistDto implements Serializable {

    private Long id;

    private LocalDateTime beginHour;
    private LocalDateTime endHour; //VIP, PREMIUM, NORMAL

    private Long concertId;
    private String artistFirstname;
    private String artistLastname;

    public PassageArtistDto() {
        super();
    }

    public PassageArtistDto(LocalDateTime beginHour, Long concertId, LocalDateTime endHour, String artistFirstname) {
        this.beginHour = beginHour;
        this.concertId = concertId;
        this.endHour = endHour;
        this.artistFirstname = artistFirstname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(LocalDateTime beginHour) {
        this.beginHour = beginHour;
    }

    public LocalDateTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalDateTime endHour) {
        this.endHour = endHour;
    }

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public String getArtistFirstname() {
        return artistFirstname;
    }

    public void setArtistFirstname(String artistFirstname) {
        this.artistFirstname = artistFirstname;
    }

    public String getArtistLastname() {
        return artistLastname;
    }

    public void setArtistLastname(String artistLastname) {
        this.artistLastname = artistLastname;
    }
}
