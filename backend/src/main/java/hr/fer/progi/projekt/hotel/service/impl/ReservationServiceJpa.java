package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.GuestRepository;
import hr.fer.progi.projekt.hotel.dao.GuestReservationRepository;
import hr.fer.progi.projekt.hotel.dao.ReservationRepository;
import hr.fer.progi.projekt.hotel.dao.ServiceRepository;
import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.Reservation;
import hr.fer.progi.projekt.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceJpa implements ReservationService {

    @Autowired
    ReservationRepository reservationRepo;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    GuestReservationRepository guestReservationRepository;
    @Autowired
    ServiceRepository serviceRepository;

    @Override
    public List<Reservation> listAll() {
        return reservationRepo.findAll();
    }

    @Override
    public List<Reservation> listAllActive() {
        List<Reservation> reservations = reservationRepo.findAll();
        reservations.removeIf(o -> o.getEndDate().before(new java.sql.Date(new java.util.Date().getTime())));
        return reservations;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        Assert.isNull(reservation.getId(), "ID rezervacije treba biti null, a ne: " + reservation.getId());
        Assert.notNull(reservation.getStartDate(), "Početni datum mora postojati");
        Assert.notNull(reservation.getEndDate(), "Krajnji datum mora postojati");
        Assert.notNull(reservation.getSpentForMinibar(), "Trošak u minibaru mora postojati");
        Assert.notNull(reservation.getGuest(), "Rezervacija mora imati goste");
        return reservationRepo.save(reservation);
    }

    @Override
    public Optional<Reservation> findById(Long reservationId) {
        Assert.notNull(reservationId, "ID se treba postaviti");
        return reservationRepo.findById(reservationId);
    }

    @Override
    public Reservation deleteReservation(Guest guest) {
        Reservation reservation = reservationRepo.findByGuest(guest).get();
        reservationRepo.delete(reservation);
        return reservation;
    }
}
