package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.MiniBar;

import java.util.List;

public interface MinibarService {

    List<MiniBar> listAll();

    void setPriceForName(String name, Long price);
}
