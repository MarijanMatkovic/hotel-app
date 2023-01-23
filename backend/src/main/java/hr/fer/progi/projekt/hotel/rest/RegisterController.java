package hr.fer.progi.projekt.hotel.rest;

import hr.fer.progi.projekt.hotel.DTOs.UpdateGuestDTO;
import hr.fer.progi.projekt.hotel.rest.security.PasswordEncoder;
import hr.fer.progi.projekt.hotel.service.GuestService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_RECEPTIONIST")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private GuestService guestService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("")
    public ResponseEntity<String> registerGuest(@RequestBody UpdateGuestDTO param){

        try {
            guestService.updateGuest(param);
        }catch (IllegalArgumentException ex){
             return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("");
    }




}
