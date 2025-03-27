package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.Organizer;
import fr.istic.taa.jaxrs.domain.Passage;
import fr.istic.taa.jaxrs.domain.Place;
import fr.istic.taa.jaxrs.domain.Price;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConcertDto implements Serializable {

    private Long id;

    private String title;
    private String description;
    private int capacity; //nullable pour signifier une capacité illimitée
    private String country;
    private String city;
    private String address;
    private Date beginDate;
    private Date endDate; //nullable
    private String repaymentConditions;
    private boolean validatedConcert;

    private Long organizerId;
    private List<Long> pricesIds = new ArrayList<Long>();
    private List<Long> placesIds = new ArrayList<Long>();
    private List<Long> passagesIds = new ArrayList<Long>();

    public ConcertDto() {
        super();
    }

    public ConcertDto(String title, String description, int capacity, String country, String city, String address, Date beginDate, Date endDate, String repaymentConditions, boolean validatedConcert) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.country = country;
        this.city = city;
        this.address = address;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.repaymentConditions = repaymentConditions;
        this.validatedConcert = validatedConcert;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isValidatedConcert() {
        return validatedConcert;
    }

    public void setValidatedConcert(boolean validatedConcert) {
        this.validatedConcert = validatedConcert;
    }

    public String getRepaymentConditions() {
        return repaymentConditions;
    }

    public void setRepaymentConditions(String repaymentConditions) {
        this.repaymentConditions = repaymentConditions;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(Long organizerId) {
        this.organizerId = organizerId;
    }

    public List<Long> getPricesIds() {
        return pricesIds;
    }

    public void setPricesIds(List<Long> pricesIds) {
        this.pricesIds = pricesIds;
    }

    public List<Long> getPlacesIds() {
        return placesIds;
    }

    public void setPlacesIds(List<Long> placesIds) {
        this.placesIds = placesIds;
    }

    public List<Long> getPassagesIds() {
        return passagesIds;
    }

    public void setPassagesIds(List<Long> passagesIds) {
        this.passagesIds = passagesIds;
    }
}
