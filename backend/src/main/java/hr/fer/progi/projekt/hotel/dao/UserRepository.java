package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.HotelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserRepository extends JpaRepository<HotelUser, Long> {
    @Query("select h from HotelUser h where h.oib like ?1")
    Optional<HotelUser> findByOib(@Nullable Long oib);

    Optional<HotelUser> findByUsername(String username);

}
