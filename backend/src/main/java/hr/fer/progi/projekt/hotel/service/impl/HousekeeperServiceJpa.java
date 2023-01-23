package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.GuestReservationRepository;
import hr.fer.progi.projekt.hotel.dao.MinibarRepository;
import hr.fer.progi.projekt.hotel.dao.ReservationRepository;
import hr.fer.progi.projekt.hotel.domain.Reservation;
import hr.fer.progi.projekt.hotel.service.HousekeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class HousekeeperServiceJpa implements HousekeeperService {

    @Autowired
    ReservationRepository reservationRepo;
    @Autowired
    GuestReservationRepository guestReservationRepository;
    @Autowired
    MinibarRepository minibarRepository;

    @Override
    public Reservation getReservationByRoomName(String roomName) {
        return guestReservationRepository.findByAccommodatingUnit_Name(roomName).get().getReservation();
    }

    @Override
    public Float calculateToday(Map<String, Integer> miniBarList){ //ovo je lista koju je spremacica unijela
        float result = 0;
        for(String x : miniBarList.keySet()){
            result += minibarRepository.findByName(x).get().getPrice() * miniBarList.get(x);
        }
        return result; //float value ukupne danasnje potrosnje
    }

    @Override
    public void updatedSpentForMinibar(String roomName, Map<String, Integer> miniBarList) {
        this.getReservationByRoomName(roomName).setSpentForMinibar(
                this.getReservationByRoomName(roomName).getSpentForMinibar() + calculateToday(miniBarList));
        reservationRepo.save(this.getReservationByRoomName(roomName));
    }
}
