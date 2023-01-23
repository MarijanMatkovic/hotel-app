package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "hall_reservation")
public class HallReservation {

    public HallReservation() {}

    public HallReservation(Date startDate, List<Hall> halls, Guest guest) {
        this.startDate = startDate;
        this.halls = halls;
        this.rentedBy = guest;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @OneToMany
    @JoinTable(name = "hall_reservation_halls",
            joinColumns = @JoinColumn(name = "hall_reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "halls_id")
    )
    private List<Hall> halls;

    @OneToOne
    @JoinTable(name = "rented_by_id")
    private Guest rentedBy;

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public HotelUser getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(Guest rentedBy) {
        this.rentedBy = rentedBy;
    }
}
