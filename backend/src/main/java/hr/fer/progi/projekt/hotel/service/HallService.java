package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Hall;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface HallService {

    Optional<Hall> findById(long hallId);

    List<Hall> findEmptyHall(String type, Date date);

}
