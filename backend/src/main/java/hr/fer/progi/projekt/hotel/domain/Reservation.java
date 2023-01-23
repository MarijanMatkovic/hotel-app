package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "reservation")
public class Reservation {

    public Reservation() {}

    public Reservation(Date startDate, Date endDate, Float spentForMinibar, Guest guest) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.spentForMinibar = spentForMinibar;
        this.guest = guest;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "spent_for_minibar", nullable = false)
    private Float spentForMinibar;

    @ManyToOne(optional = false)
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getSpentForMinibar() {
        return spentForMinibar;
    }

    public void setSpentForMinibar(Float spentForMinibar) {
        this.spentForMinibar = spentForMinibar;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
