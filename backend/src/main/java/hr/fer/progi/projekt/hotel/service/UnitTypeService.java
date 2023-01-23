package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.UnitType;

import java.util.List;

public interface UnitTypeService {

    List<UnitType> listAll();

    UnitType findByType(String type);

    void setPriceForType(String type, double price);
}
