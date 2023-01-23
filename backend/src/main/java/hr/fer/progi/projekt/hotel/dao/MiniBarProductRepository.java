package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.MiniBarProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MiniBarProductRepository extends JpaRepository<MiniBarProduct, Long> {
}