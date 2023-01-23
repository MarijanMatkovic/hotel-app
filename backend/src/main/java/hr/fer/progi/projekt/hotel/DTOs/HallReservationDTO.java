package hr.fer.progi.projekt.hotel.DTOs;

import java.sql.Date;

public class HallReservationDTO {

    private String name;
    private String type;
    private Date date;

    public HallReservationDTO(String name, String type, Date date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }
}
