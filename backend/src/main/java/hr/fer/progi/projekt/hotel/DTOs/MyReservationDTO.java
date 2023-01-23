package hr.fer.progi.projekt.hotel.DTOs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class MyReservationDTO {

    private Date start;
    private Date end;
    private String accommodatingUnit;
    private Collection<ServiceDTO> services;


    public MyReservationDTO() {
        services = new ArrayList<>();
    }

    public void addService(ServiceDTO service){
        services.add(service);
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getAccommodatingUnit() {
        return accommodatingUnit;
    }

    public void setAccommodatingUnit(String accommodatingUnit) {
        this.accommodatingUnit = accommodatingUnit;
    }

    public Collection<ServiceDTO> getServices() {
        return services;
    }

    public void setServices(Collection<ServiceDTO> services) {
        this.services = services;
    }
}
