package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Transactional
    @Modifying
    @Query("update Hotel h set h.hotelName = ?1, h.address = ?2")
    int editHotelInfo(@NonNull String hotelName, @NonNull String address);

}
