package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.HallReservation;

import java.util.Optional;

public interface HallReservationService {

    Optional<HallReservation> findById(Long hallReservationId);

    HallReservation createHallReservation(HallReservation hallReservation);

}
