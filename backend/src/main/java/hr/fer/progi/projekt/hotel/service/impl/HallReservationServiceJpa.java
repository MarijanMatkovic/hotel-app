package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.HallReservationRepository;
import hr.fer.progi.projekt.hotel.domain.HallReservation;
import hr.fer.progi.projekt.hotel.service.HallReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class HallReservationServiceJpa implements HallReservationService {

    @Autowired
    HallReservationRepository hallReservationRepository;

    @Override
    public Optional<HallReservation> findById(Long hallReservationId) {
        Assert.notNull(hallReservationId, "ID se treba postaviti");
        return hallReservationRepository.findById(hallReservationId);
    }

    @Override
    public HallReservation createHallReservation(HallReservation hallReservation) {
        Assert.isNull(hallReservation.getId(), "ID treba biti null");
        Assert.notNull(hallReservation.getRentedBy(), "Treba se postaviti tko rezervira dvoranu");
        Assert.notNull(hallReservation.getStartDate(), "Treba se postaviti datum rezervacije");
        Assert.notNull(hallReservation.getHalls(), "Treba se postaviti dvorana koja se iznajmljiva");
        return hallReservationRepository.save(hallReservation);
    }
}
