package hr.fer.progi.projekt.hotel.rest;


import hr.fer.progi.projekt.hotel.DTOs.ModelMapper;
import hr.fer.progi.projekt.hotel.DTOs.OibDTO;
import hr.fer.progi.projekt.hotel.DTOs.WorkerDTO;
import hr.fer.progi.projekt.hotel.domain.Worker;
import hr.fer.progi.projekt.hotel.rest.security.PasswordEncoder;
import hr.fer.progi.projekt.hotel.service.WorkerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@Secured({"ROLE_ADMIN", "ROLE_OWNER"})
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/new-worker")
    public ResponseEntity<String> newWorker(@RequestBody WorkerDTO worker){

        worker.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(worker.getPassword()));


        Worker newWorker = ModelMapper.DTOToWorker(worker);
        try {
            workerService.createWorker(newWorker);
        }catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body("");

    }
    @PostMapping("/remove-worker")
    public ResponseEntity<String> removeWorker(@RequestBody OibDTO oib){

        Optional<Worker> worker1 = workerService.findByOib(oib.getOib());
        if(worker1.isPresent()) {
            if(worker1.get().getRole().equals("ROLE_OWNER"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("You cant remove owner");
            if(worker1.get().getRole().equals("ROLE_ADMIN"))
                return ResponseEntity.status(HttpStatus.CONFLICT).body("You cant remove yourself");

            try {
                workerService.deleteWorker(worker1.get().getId());
            } catch (IllegalArgumentException ex) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            }
            return ResponseEntity.status(HttpStatus.OK).body("");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("worker with oib: " +oib.getOib() +" does not exists");

    }


}
