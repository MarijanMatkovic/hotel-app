package hr.fer.progi.projekt.hotel.DTOs;

import hr.fer.progi.projekt.hotel.domain.Guest;

import java.sql.Date;
import java.util.List;

public class CreateGroupReservationDTO {

    private List<Guest> users;
    private Date dateFrom;
    private Date dateTo;
    private String lunch;
    private String dinner;
    private String type;

    public CreateGroupReservationDTO(List<Guest> users, Date dateFrom, Date dateTo,
                                     String lunch, String dinner, String type) {
        this.users = users;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.lunch = lunch;
        this.dinner = dinner;
        this.type = (type.toUpperCase());
    }

    public Date getStartDate() {
        return dateFrom;
    }

    public Date getEndDate() {
        return dateTo;
    }

    public String getLunch() {
        return lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public String getAccommodatingUnitType() {
        return type;
    }

    public List<Guest> getUsers() {
        return users;
    }
}
