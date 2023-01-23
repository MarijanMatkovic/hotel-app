package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
public class Hotel {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "hotel_name", nullable = false, length = 100)
    private String hotelName;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "fax", nullable = false, length = 100)
    private String fax;

    @Column(name = "oib", nullable = false, length = 11, unique = true)
    private Long oib;

    @Column(name = "email", nullable = false, length = 100)
    private String email;


    public Hotel(String hotelName, String address) {
        this.hotelName = hotelName;
        this.address = address;
    }

    public Hotel(String hotelName, String address, String fax, Long oib, String email) {
        this.hotelName = hotelName;
        this.address = address;
        this.fax = fax;
        this.oib = oib;
        this.email = email;
    }

    public Hotel() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Long getId() {
        return id;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getOib() {
        return oib;
    }

    public void setOib(Long oib) {
        this.oib = oib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
