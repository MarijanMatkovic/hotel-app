package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccommodatingUnitRepository extends JpaRepository<AccommodatingUnit, Long> {

    Optional<AccommodatingUnit> findByName(String name);

    List<AccommodatingUnit> findByUnitType(UnitType unitType);

    Optional<AccommodatingUnit> findById(Long id);


}
