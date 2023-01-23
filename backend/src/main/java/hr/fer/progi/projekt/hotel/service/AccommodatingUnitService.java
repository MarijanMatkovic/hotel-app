package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface AccommodatingUnitService {

    List<AccommodatingUnit> listAll();
    List<AccommodatingUnit> listAllEmpty();

    AccommodatingUnit createAccommodatingUnit(AccommodatingUnit accommodatingUnit);

    Optional<AccommodatingUnit> findByName(String name);

    void removeAccommodatingUnit(AccommodatingUnit accommodatingUnit);

    AccommodatingUnit findEmptyAccommodatingUnit(String accommodatingUnitType, Date startDate, Date endDate);

    List<AccommodatingUnit> findByAccommodatingUnitType(String accommodatingUnitType);



}
