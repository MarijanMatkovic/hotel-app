package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "service")
public class Service {

    public Service() {}

    public Service(Float totalAmount, Product product, GuestReservation guestReservation) {
        this.totalAmount = totalAmount;
        this.product = product;
        this.guestReservation = guestReservation;
    }
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Float totalAmount;

    @ElementCollection
    @Column(name = "date")
    @CollectionTable(name = "service_dates", joinColumns = @JoinColumn(name = "dates_id"))
    private Set<Date> dates = new LinkedHashSet<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "guest_reservation_id", nullable = false)
    private GuestReservation guestReservation;

    @ManyToOne(optional = true)
    @JoinColumn(name = "reservation_id", nullable = true)
    private Reservation reservation;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public GuestReservation getGuestReservation() {
        return guestReservation;
    }

    public void setGuestReservation(GuestReservation guestReservation) {
        this.guestReservation = guestReservation;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<Date> dates) {
        this.dates = dates;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

}