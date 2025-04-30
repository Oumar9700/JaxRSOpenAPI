package fr.istic.taa.jaxrs.domain;

import fr.istic.taa.jaxrs.dto.AdminDto;
import fr.istic.taa.jaxrs.dto.PassageArtistDto;
import fr.istic.taa.jaxrs.dto.PassageDto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passage implements Serializable {

    private Long id;

    private LocalDateTime beginHour;
    private LocalDateTime endHour; //VIP, PREMIUM, NORMAL

    private Concert concert;
    private Artist artist;

    public Passage() {
        super();
    }

    public Passage(LocalDateTime beginHour, Concert concert, LocalDateTime endHour, Artist artist) {
        this.beginHour = beginHour;
        this.concert = concert;
        this.endHour = endHour;
        this.artist = artist;
    }

    @Id
    @GeneratedValue
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

    @ManyToOne
    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    @ManyToOne
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    //Transform Passage Object to PassageDto
    public PassageArtistDto toDto(){

        PassageArtistDto dto = new PassageArtistDto();
        dto.setId(this.getId());
        dto.setBeginHour(this.getBeginHour());
        dto.setEndHour(this.getEndHour());
        dto.setConcertId(this.getConcert().getId());
        dto.setArtistFirstname(this.getArtist().getFirstname());
        dto.setArtistLastname(this.getArtist().getLastname());

        return dto;
    }

    public PassageDto toSimpleDto(){

        PassageDto dto = new PassageDto();
        dto.setId(this.getId());
        dto.setBeginHour(this.getBeginHour());
        dto.setEndHour(this.getEndHour());
        dto.setConcertId(this.getConcert().getId());
        dto.setArtistId(this.getArtist().getId());

        return dto;
    }
}
