package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Concert implements Serializable {

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

    private Organizer organizer;
    private List<Price> prices = new ArrayList<Price>();
    private List<Place> places = new ArrayList<Place>();
    private List<Passage> passages = new ArrayList<Passage>();

    public Concert() {
        super();
    }

    public Concert(String title, String description, int capacity, String country, String city, String address, Date beginDate, Date endDate, String repaymentConditions, boolean validatedConcert) {
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

    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST)
    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST)
    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    @OneToMany(mappedBy = "concert", cascade = CascadeType.PERSIST)
    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }

    @ManyToOne
    public Organizer getOrganizer() {
        return organizer;
    }

    public void setOrganizer(Organizer organizer) {
        this.organizer = organizer;
    }
}
