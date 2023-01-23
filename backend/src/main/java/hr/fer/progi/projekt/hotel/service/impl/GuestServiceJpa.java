package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.DTOs.UpdateGuestDTO;
import hr.fer.progi.projekt.hotel.dao.*;
import hr.fer.progi.projekt.hotel.domain.*;
import hr.fer.progi.projekt.hotel.rest.security.PasswordEncoder;
import hr.fer.progi.projekt.hotel.service.GuestService;
import hr.fer.progi.projekt.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceJpa implements GuestService {

    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GuestReservationRepository guestReservationRepository;

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MinibarRepository minibarRepository;
    @Autowired
    private RefillRequestRepository refillRequestRepository;
    @Autowired
    private MiniBarProductRepository miniBarProductRepository;

    @Autowired
    private ReservationService reservationService;
    @Override
    public List<Guest> listAllGuest() {
        return guestRepository.findByOrderById();
    }

    @Override
    public List<Guest> listAllActive() {
        List<Guest> guests = guestRepository.findAll();
        List<Reservation> reservations = reservationService.listAllActive();
        guests.removeIf(l1 -> reservations.stream().noneMatch(l2 -> l2.getGuest().getUsername().equals(l1.getUsername())));
        return guests;
    }

    @Override
    public Guest createGuest(Guest guest) {
        Assert.isNull(guest.getId(), "ID gosta treba biti null, a ne: " + guest.getId());
        Assert.notNull(guest.getUsername(), "Username mora postojati");
        Assert.notNull(guest.getPassword(), "Lozinka mora postojati");
        Assert.isTrue(guestRepository.findByUsername(guest.getUsername()).isEmpty(),
                "Račun s korisničkim imenom " + guest.getUsername() + " već postoji");
        Assert.isTrue(workerRepository.findByUsername(guest.getUsername()).isEmpty(),
                "Račun s korisničkim imenom " + guest.getUsername() + " već postoji");

        return guestRepository.save(guest);
    }

    @Override
    public void updateGuest(UpdateGuestDTO dto) {
        Guest guest = findByUsername(dto.getUsername()).get();
        guest.setName(dto.getName());
        guest.setUsername(dto.getUsername());
        guest.setSurname(dto.getSurname());
        guest.setCountry(dto.getCountry());
        guest.setAddress(dto.getAddress());
        guest.setOib(dto.getOib());
        guest.setPhoneNumber(dto.getPhoneNumber());
        guest.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(dto.getPassword()));
        guestRepository.save(guest);

    }

    @Override
    public Optional<Guest> findByUsername(String username) {
        Assert.notNull(username, "Username se mora navesti");
        return guestRepository.findByUsername(username);
    }

    @Override
    public Optional<Guest> findById(Long guestId) {
        Assert.notNull(guestId, "ID se treba postaviti");
        return guestRepository.findById(guestId);
    }

    @Override
    public Guest deleteGuest(Long guestId) {
        Guest guest = fetch(guestId);
        guestRepository.delete(guest);
        return guest;
    }

    @Override
    public Guest fetch(Long guestId) {
        return findById(guestId).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Override
    public Guest fetch(String username) {
        return findByUsername(username).orElseThrow(
                IllegalArgumentException::new
        );
    }

    @Override
    public String findPasswordByUsername(String username) {
        return guestRepository.findPasswordByUsername(username);
    }

    @Override
    public Optional<GuestReservation> getGuestReservationByUsername(String username) {
        return guestReservationRepository.findGuestReservationByGuest_Username(username);
    }

    @Override
    public Optional<hr.fer.progi.projekt.hotel.domain.Service> getServiceByGuestUsernameAndServiceName(String username, String name) {
        return serviceRepository.findByGuestReservation_IdAndProduct_Type(guestReservationRepository.findGuestReservationByGuest_Username(username).get().getId() ,name);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Optional<Collection<hr.fer.progi.projekt.hotel.domain.Service>> getServicesByReservationId(Long id) {
        return serviceRepository.findAllByGuestReservation_Id(id);
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.findByType(name);
    }

    @Override
    public void addService(hr.fer.progi.projekt.hotel.domain.Service service) {
        serviceRepository.save(service);
    }

    @Override
    public void updateService(hr.fer.progi.projekt.hotel.domain.Service service) {
        serviceRepository.deleteById(service.getId());
        serviceRepository.save(service);
    }

    @Override
    public void removeService(hr.fer.progi.projekt.hotel.domain.Service service) {
        serviceRepository.delete(service);
    }

    @Override
    public Optional<MiniBar> getMinibarByName(String name) {
        return minibarRepository.findByName(name);
    }

    @Override
    public void createRefillRequest(RefillRequest refillRequest) {
        refillRequestRepository.save(refillRequest);
    }

    @Override
    public void createMinibarProduct(MiniBarProduct minibarProduct) {
        miniBarProductRepository.save(minibarProduct);
    }
}
