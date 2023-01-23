package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.BillRepository;
import hr.fer.progi.projekt.hotel.domain.Bill;
import hr.fer.progi.projekt.hotel.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BillServiceJpa implements BillService {

    @Autowired
    private BillRepository billRepository;


    @Override
    public List<Bill> listAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findByBrojRacuna(Long brojRacuna) {
        return billRepository.findById(brojRacuna);
    }

    @Override
    public void createBill(Bill bill) {
        billRepository.save(bill);
    }
}
