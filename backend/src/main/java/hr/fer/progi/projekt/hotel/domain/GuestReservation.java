package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "guest_reservation")
public class GuestReservation {

    public GuestReservation() {}

    public GuestReservation(Reservation reservation, Guest guest, AccommodatingUnit accommodatingUnit) {
        this.reservation = reservation;
        this.guest = guest;
        this.accommodatingUnit = accommodatingUnit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reservation_id", nullable = false)
    private Reservation reservation;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "guest_id", nullable = false, unique = true)
    private Guest guest;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accommodating_unit_id", nullable = false)
    private AccommodatingUnit accommodatingUnit;

    public AccommodatingUnit getAccommodatingUnit() {
        return accommodatingUnit;
    }

    public void setAccommodatingUnit(AccommodatingUnit accommodatingUnit) {
        this.accommodatingUnit = accommodatingUnit;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Long getId() {
        return id;
    }
}
