package hr.fer.progi.projekt.hotel.rest;


import hr.fer.progi.projekt.hotel.DTOs.*;
import hr.fer.progi.projekt.hotel.dao.ProductRepository;
import hr.fer.progi.projekt.hotel.domain.*;
import hr.fer.progi.projekt.hotel.rest.security.PasswordEncoder;
import hr.fer.progi.projekt.hotel.service.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;


@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/hotel")
public class OwnerController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HotelService hotelService;
    @Autowired
    private AccommodatingUnitService accommodatingUnitService;
    @Autowired
    private UnitTypeService unitTypeService;

    @Autowired
    private MinibarService minibarService;

    @Autowired
    private BillService billService;
    @Autowired
    private ProductRepository productRepository;




    @Secured("ROLE_OWNER")
    @PostMapping("/edit-info")
    public ResponseEntity<String> hotelInfo(@RequestBody HotelInfoDTO hotelInfoDTO){

        Hotel oldHotel = hotelService.listAll().get(0);
        if(hotelInfoDTO.getEmail() != null)
            oldHotel.setEmail(hotelInfoDTO.getEmail());
        if(hotelInfoDTO.getFax() != null)
            oldHotel.setFax(hotelInfoDTO.getFax());
        if(hotelInfoDTO.getHotelName() != null)
            oldHotel.setHotelName(hotelInfoDTO.getHotelName().trim().length() > 2 ?
                    hotelInfoDTO.getHotelName() : oldHotel.getHotelName());
        if(hotelInfoDTO.getOib() != null){
            Long oib = hotelInfoDTO.getOib();
            if(oib.toString().length() != 11)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("");
            oldHotel.setOib(oib);
        }
        if(hotelInfoDTO.getAddress() != null){
            oldHotel.setAddress(hotelInfoDTO.getAddress().trim().length() > 2 ?
                    hotelInfoDTO.getAddress() : oldHotel.getAddress());
        }

        try {
            hotelService.editHotelInfo(oldHotel);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @PostMapping("/edit-prices")
    public ResponseEntity<String> editPrices(@RequestBody PriceEditDTO priceEditDTO){
        boolean found = false;
        String type = priceEditDTO.getType();
        List<UnitType> list = unitTypeService.listAll();
        for(UnitType unitType : list){
            if(unitType.getType().equals(type)){
                found = true;
                unitTypeService.setPriceForType(type, priceEditDTO.getPrice());
                break;
            }
        }
        if(found)
            return ResponseEntity.status(HttpStatus.OK).body("");

        List<MiniBar> miniBarList = minibarService.listAll();
        for(MiniBar mb : miniBarList){
            if(mb.getName().equals(type)){
                found = true;
                minibarService.setPriceForName(type, priceEditDTO.getPrice());
                break;
            }
        }
        if(found)
            return ResponseEntity.status(HttpStatus.OK).body("");

        List<Product> productList = productRepository.findAll();
        for(Product p : productList){
            if(p.getType().equals(type)){
                found=true;
                productRepository.updatePriceByTypeLike(type, priceEditDTO.getPrice().floatValue());
                break;
            }
        }
        if(found)
            return ResponseEntity.status(HttpStatus.OK).body("");

        return ResponseEntity.status(HttpStatus.CONFLICT).body("");
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/prices")
    public List<PriceEditDTO> minibarInfo(){
        List<PriceEditDTO> list = new ArrayList<>();
        minibarService.listAll().stream().forEach(m -> list.add(new PriceEditDTO(m.getName(),m.getPrice().longValue())));
        productRepository.findAll().stream().forEach(p -> list.add(new PriceEditDTO(p.getType(), p.getPrice().longValue())));
        return list;
    }

    @GetMapping("/info")
    public HotelInfoDTO info(){
        Hotel hotel = hotelService.listAll().get(0);
        return new HotelInfoDTO(hotel.getAddress(), hotel.getHotelName(), hotel.getFax(), hotel.getOib(), hotel.getEmail());
    }

    @Secured("ROLE_OWNER")
    @PostMapping("/new")
    public ResponseEntity<String> createHotel(@RequestBody Hotel hotel){
        try {
            hotelService.createHotel(hotel);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/info/units")
    public List<AccommodatingUnitDTO> units(){
        List<AccommodatingUnitDTO> list = new ArrayList<>();
        accommodatingUnitService.listAll().stream().forEach(au -> list.add(ModelMapper.AccommodatingUnitToDTO(au)));
        return list;
    }


    @Secured("ROLE_OWNER")
    @PostMapping("/add-unit")
    public ResponseEntity<String> addUnit(@RequestBody AccommodatingUnitDTO auDTO){
        if(auDTO.getCapacity()<=0)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Capacity can't be zero or less");
        if(auDTO.getUnitType().equals("JEDNOKREVETNA") && auDTO.getCapacity() != 1)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Capacity needs to be one");
        if(auDTO.getUnitType().equals("DVOKREVETNA") && auDTO.getCapacity() != 2)
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Capacity needs to be two");
        AccommodatingUnit accommodatingUnit = new AccommodatingUnit(auDTO.getName(), auDTO.getCapacity(), unitTypeService.findByType(auDTO.getUnitType()));
        try {
            accommodatingUnitService.createAccommodatingUnit(accommodatingUnit);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("");

    }

    @Secured("ROLE_OWNER")
    @PostMapping("/remove-unit")
    public ResponseEntity<String> removeUnit(@RequestBody AccommodatingUnitDTO auDTO){
        Optional<AccommodatingUnit> accommodatingUnit = accommodatingUnitService.findByName(auDTO.getName());
        if(accommodatingUnit.isPresent()){
            try {
                accommodatingUnitService.removeAccommodatingUnit(accommodatingUnit.get());
            }catch (IllegalArgumentException ex){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.OK).body("");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Unit with name "+ auDTO.getName() + " does not exist");
    }


    @Secured("ROLE_OWNER")
    @PostMapping("/new-admin")
    public ResponseEntity<String> newAdmin(@RequestBody UserLoginDTO username){
        List<Worker> list;
        Optional<Worker> worker = workerService.findByUsername(username.getUsername());
        list = workerService.listAllWorkers().stream().filter(t -> t.getRole().equals("ROLE_ADMIN")).toList();
        if(worker.isPresent()){
            if(worker.get().getRole().equals("ROLE_OWNER"))
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Ne mozete postaviti vlasnika za admina");
            if(list.isEmpty()){
                try {
                    workerService.changeRole(username.getUsername(), "ROLE_ADMIN");
                }catch (IllegalArgumentException ex){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
                }
            }
            else {
                try {
                    workerService.changeRole(list.get(0).getUsername(), "ROLE_RECEPTIONIST");
                    workerService.changeRole(username.getUsername(), "ROLE_ADMIN");
                }catch (IllegalArgumentException ex){
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("");


        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Worker with username: "+ username.getUsername() +
                    " does not exist");
        }


    }

    @Secured("ROLE_OWNER")
    @PostMapping("/remove-admin")
    public ResponseEntity<String> removeAdmin(@RequestBody UserLoginDTO username){
        System.out.println(username.getUsername());
        List<Worker> list = workerService.listAllWorkers().stream().filter(t -> t.getRole().equals("ROLE_ADMIN")).toList();
        if(list.size()>0 && list.get(0).getUsername().equals(username.getUsername())){
            try {
                workerService.changeRole(username.getUsername(), "ROLE_RECEPTIONIST");
                return ResponseEntity.status(HttpStatus.OK).body("");
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }

        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Worker with username "+ username.getUsername() + " does not exists " +
                "or is not an admin");

    }


    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
    @GetMapping("/info/workers")
    public List<WorkerDTO> workers(){
        List<WorkerDTO> list = new ArrayList<>();
        workerService.listAllWorkers().forEach(w -> {
            if(!w.getRole().equals("ROLE_OWNER"))
                list.add(ModelMapper.WorkerToDTO(w));
        });
        return list;
    }


    @Secured("ROLE_OWNER")
    @GetMapping("/info/bills")
    public List<BillDTO> bills(){
        List<BillDTO> list = new ArrayList<>();
        AtomicLong ukupnaSuma = new AtomicLong(0L);
        billService.listAll().forEach(b -> {
            list.add(ModelMapper.BilltoDTO(b));
            ukupnaSuma.addAndGet(b.getIznos());
        });
        list.add(new BillDTO((long) -1, ukupnaSuma.intValue()));
        return list;
    }









}
