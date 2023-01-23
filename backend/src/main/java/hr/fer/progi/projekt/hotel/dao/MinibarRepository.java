package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.MiniBar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MinibarRepository extends JpaRepository<MiniBar, Long> {
    @Transactional
    @Modifying
    @Query("update MiniBar m set m.price = ?1 where m.name like ?2")
    void updatePriceByNameLike(Float price, @NonNull String name);

    Optional<MiniBar> findByName(String name);
}
