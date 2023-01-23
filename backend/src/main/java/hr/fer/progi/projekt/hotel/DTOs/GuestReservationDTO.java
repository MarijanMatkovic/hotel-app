package hr.fer.progi.projekt.hotel.DTOs;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.Reservation;

public class GuestReservationDTO {

    private Guest guest;

    private AccommodatingUnit accommodatingUnit;

    private Reservation reservation;

    public GuestReservationDTO(Guest guest, AccommodatingUnit accommodatingUnit, Reservation reservation) {
        this.guest = guest;
        this.accommodatingUnit = accommodatingUnit;
        this.reservation = reservation;
    }

    public Guest getGuest() {
        return guest;
    }

    public AccommodatingUnit getAccommodatingUnit() {
        return accommodatingUnit;
    }

    public Reservation getReservation() {
        return reservation;
    }

}
