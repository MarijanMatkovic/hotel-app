package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.DTOs.UpdateGuestDTO;
import hr.fer.progi.projekt.hotel.domain.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface GuestService {

    List<Guest> listAllGuest();
    List<Guest> listAllActive();

    Guest createGuest(Guest guest);

    void updateGuest(UpdateGuestDTO dto);

    Optional<Guest> findByUsername(String username);

    Optional<Guest> findById(Long guestId);

    Guest deleteGuest(Long guestId);

    Guest fetch(Long guestId);

    Guest fetch(String username);

    String findPasswordByUsername(String username);

    Optional<GuestReservation> getGuestReservationByUsername(String username);

    Optional<Service> getServiceByGuestUsernameAndServiceName(String username, String name);

    Optional<Reservation> getReservationById(Long id);

    Optional<Collection<Service>> getServicesByReservationId(Long id);

    Optional<Product> getProductByName(String name);

    void addService(Service service);

    void updateService(Service service);

    void removeService(Service service);

    Optional<MiniBar> getMinibarByName(String name);

    void createRefillRequest(RefillRequest refillRequest);

    void createMinibarProduct(MiniBarProduct minibarProduct);
}
