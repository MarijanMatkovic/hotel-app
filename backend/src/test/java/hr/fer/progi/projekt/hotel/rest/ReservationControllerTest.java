package hr.fer.progi.projekt.hotel.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.progi.projekt.hotel.DTOs.BillDTO;
import hr.fer.progi.projekt.hotel.domain.*;
import hr.fer.progi.projekt.hotel.service.BillService;
import hr.fer.progi.projekt.hotel.service.GuestReservationService;
import hr.fer.progi.projekt.hotel.service.ReservationService;
import hr.fer.progi.projekt.hotel.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNdWtpIiwicm9sZSI6IlJPTEVfUkVDRVBUSU9OSVNUIiwiZXhwIjoxNjc0NDMzOTQyfQ.dfap4Dnt9C6CH6ZyaFkgDWme1dsHNleLDpLNt1IBGhHV1iwxsb0SlZ07Omd35D6akwFgk1HYOh6HHE5g0aBX_A";

    @Autowired
    MockMvc mvc;

    @MockBean
    ReservationService reservationService;

    @MockBean
    ServiceService serviceService;

    @MockBean
    GuestReservationService guestReservationService;

    @MockBean
    BillService billService;

    @Test
    public void billCreationTest() throws Exception {
        Date start = new Date(System.currentTimeMillis());
        Date end = new Date(System.currentTimeMillis()+240000000L);
        Reservation reservation = new Reservation(start, end, 142F, new Guest("Kalamarko", "12345"));
        reservation.setId(2L);

        given(reservationService.findById(reservation.getId())).willReturn(Optional.of(reservation));
        given(serviceService.findAllByReservation(reservation)).willReturn(new LinkedList<>());
        doNothing().when(billService).createBill(any());

        List<GuestReservation> guestReservations = new ArrayList<>();

        guestReservations.add(new GuestReservation(reservation, new Guest("Kalamarko", "12345")
                , new AccommodatingUnit("101", 1, new UnitType("JEDNOKREVETNA", 20L))));
        guestReservations.add(new GuestReservation(reservation, new Guest("Patrik", "12345")
                , new AccommodatingUnit("202", 1, new UnitType("DVOKREVETNA", 50L))));
        guestReservations.add(new GuestReservation(reservation, new Guest("Spuzvabob", "12345")
                , new AccommodatingUnit("202", 1, new UnitType("DVOKREVETNA", 50L))));

        given(guestReservationService.findAllByReservation_Id(reservation.getId())).willReturn(guestReservations);

        BillDTO bill = new BillDTO();
        bill.setBrojRezervacije(2L);

        mvc.perform(post("/reservations/bill")
                        .header("Authorization", token).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(bill)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("iznos.Minibar").value(142))
                .andExpect(jsonPath("iznos.Noćenje").value(240))
                .andExpect(jsonPath("iznos.['Dodatne usluge']").value(0))
                .andExpect(jsonPath("price").value(382));
    }

}
