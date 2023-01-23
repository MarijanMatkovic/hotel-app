package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    List<Reservation> listAll();
    List<Reservation> listAllActive();

    Reservation createReservation(Reservation reservation);

    Optional<Reservation> findById(Long reservationId);
    Reservation deleteReservation(Guest guest);

}
