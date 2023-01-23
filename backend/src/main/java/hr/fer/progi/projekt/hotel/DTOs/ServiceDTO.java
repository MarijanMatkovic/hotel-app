package hr.fer.progi.projekt.hotel.DTOs;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class ServiceDTO {

    private String serviceName;
    private Set<Date> dates = new HashSet<>();

    public ServiceDTO(){}

    public ServiceDTO(String serviceName, Set<String> dates) {
        this.serviceName = serviceName;
        setDates(dates);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Set<Date> getDates() {
        return dates;
    }

    public void setDates(Set<String> dates) {
        for(String d : dates){
            this.dates.add(Date.valueOf(d.split("T")[0]));
        }
    }
}
