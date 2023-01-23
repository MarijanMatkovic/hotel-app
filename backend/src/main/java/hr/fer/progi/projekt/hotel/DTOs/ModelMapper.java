package hr.fer.progi.projekt.hotel.DTOs;


import hr.fer.progi.projekt.hotel.domain.*;

import java.sql.Date;

public class ModelMapper {

    public static Worker DTOToWorker(WorkerDTO workerDTO){

        return new Worker(workerDTO.getName(), workerDTO.getSurname(),
                workerDTO.getOib(), workerDTO.getAddress(),
                workerDTO.getPhoneNumber(), workerDTO.getUsername(),
                workerDTO.getPassword(), workerDTO.getRole(),
                Date.valueOf(workerDTO.getDateOfHire()));
    }

    /*
    Kada vraćam workera u DTO ne trebam password
     */
    public static WorkerDTO WorkerToDTO(Worker worker){
        return new WorkerDTO(worker.getName(), worker.getSurname(),
                worker.getOib(), worker.getAddress(),
                worker.getPhoneNumber(), worker.getUsername(),
                worker.getRole(),
                worker.getDateOfHire().toString());
    }

    public static AccommodatingUnitDTO AccommodatingUnitToDTO(AccommodatingUnit au){
        return new AccommodatingUnitDTO(au.getUnitType().getType(), au.getCapacity(), au.getName(), au.getUnitType().getIznos());
    }

    public static GuestReservationDTO GuestReservationToDTO(GuestReservation gr) {
        return new GuestReservationDTO(gr.getGuest(), gr.getAccommodatingUnit(), gr.getReservation());
    }

    public static BillDTO BilltoDTO(Bill bill){
        return new BillDTO(bill.getBrojRacuna(), bill.getIznos().intValue());
    }

    public static GuestDTO GuestToDTO(Guest guest) {
        return new GuestDTO(guest.getName(), guest.getSurname(),
                guest.getOib(), guest.getAddress(),
                guest.getPhoneNumber(), guest.getUsername(),
                guest.getCountry(), guest.getAccommodatingUnit().getName());
    }

    public static ReservationDTO ReservationToDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getGuest().getUsername(), reservation.getStartDate(), reservation.getEndDate());
    }
}
