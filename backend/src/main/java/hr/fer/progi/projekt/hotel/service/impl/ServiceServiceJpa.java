package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.ServiceRepository;
import hr.fer.progi.projekt.hotel.domain.Reservation;
import hr.fer.progi.projekt.hotel.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ServiceServiceJpa implements ServiceService {

    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public hr.fer.progi.projekt.hotel.domain.Service createService(hr.fer.progi.projekt.hotel.domain.Service service) {
        Assert.isNull(service.getId(), "ID servisa treba biti null");
        Assert.notNull(service.getTotalAmount(), "Količina ne smije biti null");
        Assert.notNull(service.getProduct(), "Trebaju postojati produkti za servis");
        Assert.notNull(service.getGuestReservation(), "Za servis treba postojati guestReservation");
        return serviceRepository.save(service);
    }

    @Override
    public List<hr.fer.progi.projekt.hotel.domain.Service> findAllByReservation(Reservation reservation) {
        return serviceRepository.findAllByReservation_Id(reservation.getId());
    }

}
