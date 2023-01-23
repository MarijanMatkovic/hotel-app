package hr.fer.progi.projekt.hotel.rest;

import hr.fer.progi.projekt.hotel.DTOs.MinibarReportDTO;
import hr.fer.progi.projekt.hotel.DTOs.MyReservationDTO;
import hr.fer.progi.projekt.hotel.DTOs.ServiceDTO;
import hr.fer.progi.projekt.hotel.domain.*;
import hr.fer.progi.projekt.hotel.service.AccommodatingUnitService;
import hr.fer.progi.projekt.hotel.service.GuestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Secured("ROLE_GUEST")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/myreservation")
public class GuestController {


    @Autowired
    private GuestService guestService;
    @Autowired
    private AccommodatingUnitService accommodatingUnitService;

    @GetMapping()
    public ResponseEntity<MyReservationDTO> ReservationInfo(@RequestHeader("Authorization") String token){

        String username = getUsernameFromToken(token);

        Optional<GuestReservation> guestReservation = guestService.getGuestReservationByUsername(username);

        if(guestReservation.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        MyReservationDTO response = new MyReservationDTO();
        response.setStart(guestReservation.get().getReservation().getStartDate());
        response.setEnd(guestReservation.get().getReservation().getEndDate());
        response.setAccommodatingUnit(guestReservation.get().getAccommodatingUnit().getName());

        var services = guestService.getServicesByReservationId(guestReservation.get().getId());

        if(services.isPresent() && !services.get().isEmpty()){
            for(var service : services.get()){
                response.addService(new ServiceDTO(service.getProduct().getType(), service.getDates().stream().map(Date::toString).collect(Collectors.toSet())));
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/add-service")
    public ResponseEntity<String> addService(@RequestBody ServiceDTO serviceDTO, @RequestHeader("Authorization") String token){

        String username = getUsernameFromToken(token);

        var guestReservation = guestService.getGuestReservationByUsername(username);

        var product = guestService.getProductByName(serviceDTO.getServiceName());

        var service = guestService.getServiceByGuestUsernameAndServiceName(username, serviceDTO.getServiceName());

        if(service.isPresent()){
            service.get().getDates().addAll(serviceDTO.getDates());
            service.get().setTotalAmount(service.get().getDates().size() * service.get().getProduct().getPrice());
            guestService.updateService(service.get());
        }else{
            Service newService = new Service();
            newService.setDates(serviceDTO.getDates());
            newService.setProduct(product.get());
            newService.setGuestReservation(guestReservation.get());
            newService.setTotalAmount(product.get().getPrice() * serviceDTO.getDates().size());
            try {
                guestService.addService(newService);
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Uspjesno dodana usluga!");
    }

    @PostMapping("/remove-service")
    public ResponseEntity<String> removeService(@RequestBody ServiceDTO serviceDTO, @RequestHeader("Authorization") String token){

        var service = guestService.getServiceByGuestUsernameAndServiceName(getUsernameFromToken(token), serviceDTO.getServiceName()).get();

        if(serviceDTO.getDates().equals(service.getDates())){
            try {
                guestService.removeService(service);
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }else{
            service.getDates().removeAll(serviceDTO.getDates());
            service.setTotalAmount(service.getDates().size() * service.getProduct().getPrice());
            try {
                guestService.updateService(service);
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body("Uspjesno uklonjena usluga!");
    }

    //ne koristi se
    @PostMapping("/refill-minibar")
    public ResponseEntity<String> refillMiniBar(@RequestBody MinibarReportDTO request){

        RefillRequest refillRequest = new RefillRequest();
        refillRequest.setAccommodatingUnit(accommodatingUnitService.findByName(request.getRoomName()).get());

        guestService.createRefillRequest(refillRequest);

        for(var p : request.getMinibarList().keySet()){
            MiniBar product = guestService.getMinibarByName(p).get();
            MiniBarProduct minibarProduct = new MiniBarProduct();
            minibarProduct.setProduct(product);
            minibarProduct.setRefillRequest(refillRequest);
            minibarProduct.setAmount(request.getMinibarList().get(p));

            guestService.createMinibarProduct(minibarProduct);
        }

        return ResponseEntity.status(HttpStatus.OK).body("Uspjesno poslan zahtjev za nadopunu mini-bara");
    }

    private String getUsernameFromToken(String token){
        return new String(Base64.getUrlDecoder().decode(token.split("\\.")[1])).split(",")[0].split(":")[1].replace("\"", "");
    }
}
