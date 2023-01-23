package hr.fer.progi.projekt.hotel.rest;

import hr.fer.progi.projekt.hotel.DTOs.MinibarReportDTO;
import hr.fer.progi.projekt.hotel.domain.MiniBar;
import hr.fer.progi.projekt.hotel.service.HousekeeperService;
import hr.fer.progi.projekt.hotel.service.MinibarService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured("ROLE_HOUSEKEEPER")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/housekeeper")
public class HousekeeperController {
    /*
    iz tablice bi trebali povuc minibar(dropdown neki)
    ona unosi coca cola x2 itd, i onda se na backu to racuna
    onda napravit reservation.setSpentForMinibar = getSpentForMinibar + izracunato

    te, if(izvjestaj.date == reservation.endDate) posalji obavijest soba spremna za novog gosta.
     */

    //primit post body kao neki objekt
/*



    @RequestBody
    {
        reservationId : id,
        minibar : {
            cola : 5,
            fanta : 8,
            snack : 2
        }
    }
    */

    @Autowired
    private HousekeeperService housekeeperService;

    @Autowired
    private MinibarService minibarService;

    @GetMapping("/housekeeper")
    public List<MiniBar> listMinibarOptions(){
        return minibarService.listAll();
    }



    @PostMapping("/housekeeper")
    public boolean inspectRoom(@RequestBody MinibarReportDTO report){//JSON poslani treba pretvorit u neki kurac
        /*
        {
            roomName: 202,
            minibar: [{
                fanta: 4,
                snickers: 2,
                pepsi: 1
            }]
        }
        List x = {fanta*4, coca-cola*2,
         */
        try {
            housekeeperService.updatedSpentForMinibar(report.getRoomName(), report.getMinibarList());
            System.out.println(housekeeperService.calculateToday(report.getMinibarList()));

            System.out.println(housekeeperService.getReservationByRoomName(report.getRoomName()).getEndDate().toLocalDate());
            System.out.println(java.time.LocalDate.now());

            if(housekeeperService.getReservationByRoomName(report.getRoomName()).getEndDate().toLocalDate().equals(java.time.LocalDate.now())){
                System.out.println("Soba je spremna za novog gosta!!!");
            }
            return true;
        } catch (Exception e){
            return false;
        }

    }
}
/*
skontat kako iz broja sobe dobit idrezervacije


znaci accomodating unit(id - brojSobe) mapirat na dropdown, sobarica oznacava koja je soba

i onda se ta soba preko




-----------

preko guestReservationa proc po imenu sobe trazit prvog gosta koji ima ime sobe isto ko to navedeno ime

i na taj nacin dohvatit rezervaciju i onda na njoj :


znaci accomodating unit(id - brojSobe) mapirat na dropdown, sobarica oznacava koja je soba

i onda se ta soba preko


for(guest_reserv : guestreservations){
    if(guest_reservation.getAccomodatingUnit.getName == inputRoomName){
        to je ta soba...
        rezervacija = guest_reservation.getReservation();
        break;
    }
}

sad imamo Reservation i na njemu mozemo to sve radit


 */
