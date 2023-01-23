package hr.fer.progi.projekt.hotel.rest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.progi.projekt.hotel.DTOs.UserLoginDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static hr.fer.progi.projekt.hotel.rest.security.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            final UserLoginDTO user = new ObjectMapper().readValue(request.getInputStream(), UserLoginDTO.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            ));
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult){
        final String token = JWT.create().withSubject(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername())
                .withClaim("role", userDetailsService.loadUserByUsername(((org.springframework.security.core.userdetails.User) authResult.getPrincipal()).getUsername()).getAuthorities().stream().toList().get(0).toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).sign(Algorithm.HMAC512(SECRET.getBytes()));
        response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
