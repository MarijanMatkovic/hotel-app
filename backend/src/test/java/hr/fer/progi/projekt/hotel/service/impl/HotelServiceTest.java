package hr.fer.progi.projekt.hotel.service.impl;


import hr.fer.progi.projekt.hotel.domain.Hotel;
import hr.fer.progi.projekt.hotel.service.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HotelServiceTest {

    @Autowired
    HotelService hotelService;


    @Test
    public void cannotCreateAnotherHotel(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> hotelService.createHotel(new Hotel()));
    }
}


