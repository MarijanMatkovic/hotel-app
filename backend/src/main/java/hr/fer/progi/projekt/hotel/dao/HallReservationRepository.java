package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.HallReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallReservationRepository extends JpaRepository<HallReservation, Long> {}
