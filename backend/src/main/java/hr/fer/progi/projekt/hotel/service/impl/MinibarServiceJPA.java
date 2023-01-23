package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.MinibarRepository;
import hr.fer.progi.projekt.hotel.domain.MiniBar;
import hr.fer.progi.projekt.hotel.service.MinibarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MinibarServiceJPA implements MinibarService {
    @Autowired
    MinibarRepository minibarRepository;

    @Override
    public List<MiniBar> listAll() {
        return minibarRepository.findAll();
    }

    @Override
    public void setPriceForName(String name, Long price) {
        minibarRepository.updatePriceByNameLike(price.floatValue(), name);
    }
}
