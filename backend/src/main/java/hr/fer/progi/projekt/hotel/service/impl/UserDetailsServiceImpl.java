package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.UserRepository;
import hr.fer.progi.projekt.hotel.domain.HotelUser;
import hr.fer.progi.projekt.hotel.service.HotelUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements HotelUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("unchecked")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<HotelUser> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        final HotelUser user = optionalUser.get();

        List<SimpleGrantedAuthority> grantedAuthorities = (List<SimpleGrantedAuthority>) user.getAuthorities();

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    public Optional<HotelUser> findByOib(Long oib) {
        return userRepository.findByOib(oib);
    }
}