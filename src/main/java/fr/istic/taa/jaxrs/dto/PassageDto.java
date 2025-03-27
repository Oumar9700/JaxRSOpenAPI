package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Artist;
import fr.istic.taa.jaxrs.domain.Concert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PassageDto implements Serializable {

    private Long id;

    private LocalDateTime beginHour;
    private LocalDateTime endHour; //VIP, PREMIUM, NORMAL

    private Long concertId;
    private Long artistId;

    public PassageDto() {
        super();
    }

    public PassageDto(LocalDateTime beginHour, Long concertId, LocalDateTime endHour, Long artistId) {
        this.beginHour = beginHour;
        this.concertId = concertId;
        this.endHour = endHour;
        this.artistId = artistId;
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

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }
}
