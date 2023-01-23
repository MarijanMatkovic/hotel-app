package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HallRepository extends JpaRepository<Hall, Long> {
}
