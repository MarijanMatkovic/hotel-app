package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Bill;

import java.util.List;
import java.util.Optional;


public interface BillService {

    public List<Bill> listAll();

    public Optional<Bill> findByBrojRacuna(Long brojRacuna);

    void createBill(Bill bill);
}
