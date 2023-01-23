package hr.fer.progi.projekt.hotel.DTOs;

import java.sql.Date;

public class ReservationDTO {

    private Long id;
    private String username;
    private Date startDate;
    private Date endDate;

    public ReservationDTO() {}

    public ReservationDTO(Long id, String username, Date startDate, Date endDate) {
        this.id = id;
        this.username = username;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
