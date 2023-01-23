package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    Optional<Service> findByGuestReservation_IdAndProduct_Type(Long id, String type);

    Optional<Collection<Service>> findAllByGuestReservation_Id(Long id);

    List<Service> findAllByReservation_Id(Long id);


}
