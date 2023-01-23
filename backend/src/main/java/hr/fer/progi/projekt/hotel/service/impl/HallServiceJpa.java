package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.HallRepository;
import hr.fer.progi.projekt.hotel.dao.HallReservationRepository;
import hr.fer.progi.projekt.hotel.domain.Hall;
import hr.fer.progi.projekt.hotel.domain.HallReservation;
import hr.fer.progi.projekt.hotel.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HallServiceJpa implements HallService {

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private HallReservationRepository hallReservationRepository;

    @Override
    public Optional<Hall> findById(long hallId) {
        Assert.notNull(hallId, "ID se treba postaviti");
        return hallRepository.findById(hallId);
    }

    @Override
    public List<Hall> findEmptyHall(String type, Date date) {
        Assert.notNull(type, "Tip dvorane se mora postaviti");
        List<Hall> halls = hallRepository.findAll();
        for(HallReservation h : hallReservationRepository.findAll()) {
            if(h.getStartDate().toLocalDate().equals(date.toLocalDate()))
                halls.removeAll(h.getHalls());
        }
        if (type.equals("dvorana")) {
            if(halls.size() > 1)
                halls.remove(1);
            Assert.notEmpty(halls, "Sve dvorane su zauzete");
        } else {
            Assert.notEmpty(halls, "Sve dvorane su zauzete");
            Assert.isTrue(halls.size() > 1, "Nije moguće rezervirati obje dvorane");
        }
        return halls;
    }
}
