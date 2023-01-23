package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "guest")
public class Guest extends HotelUser {

    public Guest() {}

    public Guest(String username, String password) {
        super(username, password);
    }

    public Guest(String username, String password, AccommodatingUnit accommodatingUnit) {
        super(username, password);
        this.accommodatingUnit = accommodatingUnit;
    }

    public Guest(String name, String surname, String oib, String address, String  phoneNumber, String username,
                 String password, String country, AccommodatingUnit accommodatingUnit) {
        super(name, surname, oib, address, phoneNumber, username, password);
        this.country = country;
        this.accommodatingUnit = accommodatingUnit;
    }

    @Column(name = "country", length = 50)
    private String country;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "accommodating_unit_id", nullable = false)
    private AccommodatingUnit accommodatingUnit;

    public AccommodatingUnit getAccommodatingUnit() {
        return accommodatingUnit;
    }

    public void setAccommodatingUnit(AccommodatingUnit accommodatingUnit) {
        this.accommodatingUnit = accommodatingUnit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}
