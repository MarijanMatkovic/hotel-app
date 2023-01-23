package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Reservation;

import java.util.Map;

public interface HousekeeperService {

    Reservation getReservationByRoomName(String roomName);

    Float calculateToday(Map<String, Integer> miniBarList);

    void updatedSpentForMinibar(String roomName, Map<String, Integer> miniBarList);
}
