package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.AccommodatingUnitRepository;
import hr.fer.progi.projekt.hotel.dao.GuestReservationRepository;
import hr.fer.progi.projekt.hotel.dao.UnitTypeRepository;
import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.service.AccommodatingUnitService;
import hr.fer.progi.projekt.hotel.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodatingUnitServiceJpa implements AccommodatingUnitService {

    @Autowired
    private AccommodatingUnitRepository accommodatingUnitRepository;

    @Autowired
    private GuestReservationRepository guestReservationRepository;
    @Autowired
    private UnitTypeRepository unitTypeRepository;
    @Autowired
    private GuestService guestService;

    @Override
    public List<AccommodatingUnit> listAll() {
        return accommodatingUnitRepository.findAll();
    }

    @Override
    public List<AccommodatingUnit> listAllEmpty() {
        List<AccommodatingUnit> accommodatingUnits = accommodatingUnitRepository.findAll();
        List<AccommodatingUnit> usedAccommodatingUnits = accommodatingUnitRepository.findAll();
        List<Guest> guests = guestService.listAllActive();
        usedAccommodatingUnits.removeIf(a1 -> guests.stream().
                noneMatch(a2 -> a2.getAccommodatingUnit().getId().equals(a1.getId())));
        accommodatingUnits.removeAll(usedAccommodatingUnits);
        return accommodatingUnits;
    }

    @Override
    public AccommodatingUnit createAccommodatingUnit(AccommodatingUnit accommodatingUnit) {
        Assert.isNull(accommodatingUnit.getId(), "ID mora biti null");
        Assert.notNull(accommodatingUnit.getName(), "Ime mora postojati");
        Assert.isTrue(accommodatingUnitRepository.findByName(accommodatingUnit.getName()).isEmpty(),
                "Jedinica s imenom " + accommodatingUnit.getName() + "već postoji");

        return accommodatingUnitRepository.save(accommodatingUnit);
    }

    @Override
    public Optional<AccommodatingUnit> findByName(String name) {
        return accommodatingUnitRepository.findByName(name);
    }

    @Override
    public void removeAccommodatingUnit(AccommodatingUnit accommodatingUnit) {
        Assert.isTrue(accommodatingUnitRepository.findByName(accommodatingUnit.getName()).isPresent(),
                "Jedinica s imenom " + accommodatingUnit.getName() + " ne postoji");

        accommodatingUnitRepository.delete(accommodatingUnit);
    }

    @Override
    public List<AccommodatingUnit> findByAccommodatingUnitType(String accommodatingUnitType) {
        Assert.notNull(accommodatingUnitType, "Tip se mora postaviti");
        return accommodatingUnitRepository.findByUnitType(unitTypeRepository.findByType(accommodatingUnitType));
    }

    @Override
    public AccommodatingUnit findEmptyAccommodatingUnit(String accommodatingUnitType, Date startDate, Date endDate) {
        Assert.notNull(accommodatingUnitType, "Tip se mora postaviti");
        List<AccommodatingUnit> list = accommodatingUnitRepository.findByUnitType(unitTypeRepository.findByType(accommodatingUnitType));
        AccommodatingUnit au = null;
        for (AccommodatingUnit accommodatingUnit : list) {
            if(guestReservationRepository.findByAccommodatingUnit(accommodatingUnit).isEmpty() ||
                    guestReservationRepository.findByAccommodatingUnit(accommodatingUnit).stream()
                            .allMatch(a -> a.getReservation().getStartDate().after(endDate)) ||
                    guestReservationRepository.findByAccommodatingUnit(accommodatingUnit).stream()
                            .allMatch(a  -> a.getReservation().getEndDate().before(startDate))) {
                au = accommodatingUnit;
            }
        }
        Assert.notNull(au,"Smještajna jedinica već zauzeta");
        return au;
    }

}
