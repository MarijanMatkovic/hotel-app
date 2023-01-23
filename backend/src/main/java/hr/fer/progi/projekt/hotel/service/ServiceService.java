package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Reservation;
import hr.fer.progi.projekt.hotel.domain.Service;

import java.util.List;

public interface ServiceService {

    Service createService(Service service);

    List<Service> findAllByReservation(Reservation reservation);

}
