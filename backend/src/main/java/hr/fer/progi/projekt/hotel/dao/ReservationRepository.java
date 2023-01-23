package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Guest;
import hr.fer.progi.projekt.hotel.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByOrderById();

    @NonNull
    Optional<Reservation> findById(@NonNull Long id);

    Optional<Reservation> findByGuest(Guest guest);


}
