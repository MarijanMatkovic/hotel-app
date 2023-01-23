package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.HotelRepository;
import hr.fer.progi.projekt.hotel.domain.Hotel;
import hr.fer.progi.projekt.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class HotelServiceJpa implements HotelService {

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public List<Hotel> listAll(){
        return hotelRepo.findAll();
    }



    @Override
    public Hotel createHotel(Hotel hotel){
        Assert.notNull(hotel, "Hotel object must be given");
        Assert.isTrue((hotelRepo.count() == 0), "Only one instance of Hotel can be created");

        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel editHotelInfo(Hotel hotel) {
        Assert.notNull(hotel, "Hotel object must be given");
        Assert.isTrue(hotelRepo.existsById(1L), "Hotel does not exists");

        hotelRepo.editHotelInfo(hotel.getHotelName(), hotel.getAddress());
        return hotelRepo.findAll().get(0);
    }

}
