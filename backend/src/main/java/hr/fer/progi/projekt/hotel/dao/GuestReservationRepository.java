package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.GuestReservation;
import hr.fer.progi.projekt.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GuestReservationRepository extends JpaRepository<GuestReservation, Long> {

    List<GuestReservation> findByOrderById();

    Optional<GuestReservation> findByReservation(Reservation reservation);

    Optional<GuestReservation> findByGuest(Guest guest);

    List<GuestReservation> findByAccommodatingUnit(AccommodatingUnit accommodatingUnit);


    //getaccomunit i getreservation

    Optional<GuestReservation> findByAccommodatingUnit_Name(String name);
/*
imam ime sobe
ovim dobijam guestReservation iz tog imena Sobe
 */
/*
{
  "name": "Ivana",
  "surname": "Ivana",
  "oib": "12345678915",
  "address": "Unska ulica 3",
  "phoneNumber": "0911234567",
  "username": "Ivana",
  "password": "password",
  "role": "ROLE_HOUSEKEEPER",
  "dateOfHire": "2000-12-12"
}
 */

    public Optional<GuestReservation> findGuestReservationById(Long id);

    public Optional<GuestReservation> findGuestReservationByGuest_Username(String username);

    public Optional<GuestReservation> findFirstByAccommodatingUnit_Name(String name);

    List<GuestReservation> findAllByReservation_Id(Long id);
}
