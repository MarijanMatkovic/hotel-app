package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Hotel;

import java.util.List;

public interface HotelService {

    List<Hotel> listAll();

    Hotel createHotel(Hotel hotel);

    Hotel editHotelInfo(Hotel hotel);
}
