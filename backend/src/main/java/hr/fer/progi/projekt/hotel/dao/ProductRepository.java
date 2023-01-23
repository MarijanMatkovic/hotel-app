package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Transactional
    @Modifying
    @Query("update Product p set p.price = ?2 where p.type like ?1")
    void updatePriceByTypeLike(@NonNull String type, float price);
    @Override
    @NonNull
    public Optional<Product> findById(@NonNull Long id);

    public Optional<Product> findByType(String name);

    public List<Product> findAll();
}
