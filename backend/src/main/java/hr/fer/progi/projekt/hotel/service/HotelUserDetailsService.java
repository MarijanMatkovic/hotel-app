package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.HotelUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface HotelUserDetailsService extends UserDetailsService {

    Optional<HotelUser> findByOib(Long oib);

}
