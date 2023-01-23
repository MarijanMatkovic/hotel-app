package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

}