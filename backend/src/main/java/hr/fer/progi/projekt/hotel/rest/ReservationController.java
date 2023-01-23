package hr.fer.progi.projekt.hotel.rest;

import hr.fer.progi.projekt.hotel.DTOs.*;
import hr.fer.progi.projekt.hotel.dao.ProductRepository;
import hr.fer.progi.projekt.hotel.dao.ReservationRepository;
import hr.fer.progi.projekt.hotel.domain.*;
import hr.fer.progi.projekt.hotel.rest.security.PasswordEncoder;
import hr.fer.progi.projekt.hotel.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@Secured({"ROLE_RECEPTIONIST", "ROLE_OWNER", "ROLE_ADMIN"})
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private GuestReservationService guestReservationService;

    @Autowired
    private AccommodatingUnitService accommodatingUnitService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private GuestService guestService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private HallService hallService;

    @Autowired
    private HallReservationService hallReservationService;

    @Autowired
    private BillService billService;
    @Autowired
    private ReservationRepository reservationRepository;

    @Secured({"ROLE_RECEPTIONIST", "ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("")
    public List<ReservationDTO> listReservations(){
        List<ReservationDTO> list = new ArrayList<>();
        reservationService.listAllActive().forEach(r -> list.add(ModelMapper.ReservationToDTO(r)));
        return list;
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/guests")
    public List<GuestDTO> guests(){
        List<GuestDTO> list = new ArrayList<>();
        guestService.listAllActive().forEach(g -> list.add(ModelMapper.GuestToDTO(g)));
        return list;
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/emptyRooms")
    public List<AccommodatingUnitDTO> emptyRooms() {
        List<AccommodatingUnitDTO> list = new ArrayList<>();
        accommodatingUnitService.listAllEmpty().forEach(a -> list.add(ModelMapper.AccommodatingUnitToDTO(a)));
        return list;
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @PostMapping("/cancelReservations")
    public ResponseEntity<String> cancelReservation(@RequestBody UsernameDTO user) {
        Optional<Guest> guest = guestService.findByUsername(user.getUsername());
        if(guest.isPresent()) {
            try {
                reservationService.deleteReservation(guest.get());
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Guest with username: " + user.getUsername() + " does not exists");
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @PostMapping("/new")
    public ResponseEntity<String> createGroupReservation(@RequestBody CreateGroupReservationDTO myReservation){

        Assert.isTrue(((myReservation.getEndDate().getTime() - myReservation.getStartDate().getTime()) / (1000 * 60 * 60 * 24)) % 365 >= 3, "Rezervacija mora trajati barem 3 dana");

        AccommodatingUnit accommodatingUnit = accommodatingUnitService.findEmptyAccommodatingUnit
                (myReservation.getAccommodatingUnitType(), myReservation.getStartDate(), myReservation.getEndDate());

        Assert.isTrue(myReservation.getUsers().size() <= accommodatingUnit.getCapacity(),
                "Postavljeno više gostiju nego što je moguće za tip smještajne jedinice");

        for(Guest user : myReservation.getUsers()) {

            Guest guest = new Guest(user.getName(),
                    passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()), accommodatingUnit);
            try {
                guestService.createGuest(guest);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

            Reservation reservation = new Reservation(myReservation.getStartDate(),
                    myReservation.getEndDate(), 0f, guest);

            try {
                reservationService.createReservation(reservation);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

            GuestReservation guestReservation = new GuestReservation(reservation,
                    guest, accommodatingUnit);

            try {
                guestReservationService.createGuestReservation(guestReservation);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

            Product breakfast = productRepository.findByType("BREAKFAST").get();
            Service serviceBreakfast = new Service(1f, breakfast, guestReservation);

            try {
                serviceService.createService(serviceBreakfast);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

            if(myReservation.getLunch() != null) {
                Product lunch = productRepository.findByType("LUNCH").get();
                Service serviceLunch = new Service(1f, lunch, guestReservation);

                try {
                    serviceService.createService(serviceLunch);
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
                }
            }

            if(myReservation.getDinner() != null) {
                Product dinner = productRepository.findByType("DINNER").get();
                Service serviceDinner = new Service(1f, dinner, guestReservation);

                try {
                    serviceService.createService(serviceDinner);
                } catch (IllegalArgumentException e) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
                }
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("");

    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_ADMIN"})
    @PostMapping("/hall")
    public ResponseEntity<String> createHallReservation(@RequestBody HallReservationDTO hallReservationDTO) {

        List<Hall> halls = hallService.findEmptyHall(hallReservationDTO.getType(), hallReservationDTO.getDate());
        Guest guest = guestService.findByUsername(hallReservationDTO.getName()).get();
        HallReservation hallReservation = new HallReservation(hallReservationDTO.getDate(), halls, guest);

        try {
            hallReservationService.createHallReservation(hallReservation);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_OWNER", "ROLE_ADMIN"})
    @PostMapping("/bill")
    public ResponseEntity<BillDTO> createBill(@RequestBody BillDTO billDTO){
        float price = 0;
        Map<String, Float> map = new HashMap<>();
        if(billDTO.getBrojRezervacije() < 0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Optional<Reservation> reservation = reservationService.findById(billDTO.getBrojRezervacije());
        if(reservation.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

//        Optional<Bill> bill = billService.findByBrojRacuna(billDTO.getBrojRezervacije());
//        if(bill.isPresent())
//            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(new BillDTO(billDTO.getBrojRezervacije(), bill.get().getIznos().intValue()));

        Reservation res = reservation.get();
        map.put("Minibar", res.getSpentForMinibar());
        price += serviceService.findAllByReservation(res).stream().mapToDouble(Service::getTotalAmount).sum();
        map.put("Dodatne usluge", price);
        long brojDana = res.getEndDate().getTime() - res.getStartDate().getTime();
        brojDana = TimeUnit.MILLISECONDS.toDays(brojDana);
        List<GuestReservation> list = guestReservationService.findAllByReservation_Id(res.getId());
        price = 0;
        for(GuestReservation guestReservation : list){
            price += guestReservation.getAccommodatingUnit().getUnitType().getIznos() * brojDana;
        }
        map.put("Noćenje", price);

        try {
            billService.createBill(new Bill(billDTO.getBrojRezervacije(), map.values().stream().mapToLong(Float::longValue).sum()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BillDTO(billDTO.getBrojRezervacije(), map));

    }

    @Secured({"ROLE_RECEPTIONIST", "ROLE_OWNER", "ROLE_ADMIN", "ROLE_HOUSEKEEPER"})
    @GetMapping("/in-use")
    public ResponseEntity<Set<AccommodatingUnitDTO>> unitsInUse(){
        LocalDate today = new Date(System.currentTimeMillis()).toLocalDate();

        Set<AccommodatingUnitDTO> inUse = guestReservationService.listAll().stream()
                .filter(r -> (today.isEqual(r.getReservation().getEndDate().toLocalDate())
                        || today.isBefore(r.getReservation().getEndDate().toLocalDate()))
                        && (r.getReservation().getStartDate().toLocalDate().isBefore(today)
                        || r.getReservation().getStartDate().toLocalDate().isEqual(today)))
                .map(r -> ModelMapper.AccommodatingUnitToDTO(r.getAccommodatingUnit())).collect(Collectors.toSet());

        return ResponseEntity.status(HttpStatus.OK).body(inUse);
    }

}
