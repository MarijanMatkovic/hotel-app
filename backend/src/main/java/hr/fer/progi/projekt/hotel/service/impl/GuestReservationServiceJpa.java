package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.AccommodatingUnitRepository;
import hr.fer.progi.projekt.hotel.dao.GuestRepository;
import hr.fer.progi.projekt.hotel.dao.GuestReservationRepository;
import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.GuestReservation;
import hr.fer.progi.projekt.hotel.domain.Reservation;
import hr.fer.progi.projekt.hotel.service.GuestReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class GuestReservationServiceJpa implements GuestReservationService {

    @Autowired
    GuestReservationRepository guestReservationRepository;

    @Autowired
    AccommodatingUnitRepository accommodatingUnitRepository;
    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<GuestReservation> listAll() {
        return guestReservationRepository.findAll();
    }

    @Override
    public GuestReservation createGuestReservation(GuestReservation guestReservation) {
        Assert.isNull(guestReservation.getId(), "ID treba biti null");
        Assert.notNull(guestReservation.getReservation(), "Rezervacija se treba postaviti");
        Assert.notNull(guestReservation.getGuest(), "Gost se treba postaviti");
        Assert.notNull(guestReservation.getAccommodatingUnit(), "Smještajna jedinica se treba postaviti");
        return guestReservationRepository.save(guestReservation);
    }

    @Override
    public Optional<GuestReservation> findById(Long guestReservationId) {
        Assert.notNull(guestReservationId, "ID se treba postaviti");
        return guestReservationRepository.findById(guestReservationId);
    }

    @Override
    public Optional<GuestReservation> findByReservation(Reservation reservation) {
        Assert.notNull(reservation, "Rezervacija se treba postaviti");
        return guestReservationRepository.findByReservation(reservation);
    }

    @Override
    public Optional<GuestReservation> findByGuest(Guest guest) {
        Assert.notNull(guest, "Gost se treba postaviti");
        return guestReservationRepository.findByGuest(guest);
    }

    @Override
    public List<GuestReservation> findByAccommodatingUnit(AccommodatingUnit accommodatingUnit) {
        Assert.notNull(accommodatingUnit, "Smještajna jedinica se treba postaviti");
        return guestReservationRepository.findByAccommodatingUnit(accommodatingUnit);
    }

    @Override
    public List<GuestReservation> findAllByReservation_Id(Long id) {
        return guestReservationRepository.findAllByReservation_Id(id);
    }

}
