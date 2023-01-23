package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.GuestReservation;
import hr.fer.progi.projekt.hotel.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface GuestReservationService {

    List<GuestReservation> listAll();

    GuestReservation createGuestReservation(GuestReservation guestReservation);

    Optional<GuestReservation> findById(Long guestReservationId);

    Optional<GuestReservation> findByReservation(Reservation reservation);

    Optional<GuestReservation> findByGuest(Guest guest);

    List<GuestReservation> findByAccommodatingUnit(AccommodatingUnit accommodatingUnit);

    List<GuestReservation> findAllByReservation_Id(Long id);
}
