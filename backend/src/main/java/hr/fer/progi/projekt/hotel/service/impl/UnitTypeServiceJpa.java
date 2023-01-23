package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.UnitTypeRepository;
import hr.fer.progi.projekt.hotel.domain.UnitType;
import hr.fer.progi.projekt.hotel.service.UnitTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitTypeServiceJpa implements UnitTypeService {

    @Autowired
    private UnitTypeRepository unitTypeRepository;

    @Override
    public List<UnitType> listAll() {
        return unitTypeRepository.findAll();
    }

    @Override
    public UnitType findByType(String type) {
        return unitTypeRepository.findByType(type);
    }

    @Override
    public void setPriceForType(String type, double price) {
        unitTypeRepository.updateIznosByTypeLike((long) price, type);
    }


}
