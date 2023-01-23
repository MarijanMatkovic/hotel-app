package hr.fer.progi.projekt.hotel.rest;

import hr.fer.progi.projekt.hotel.domain.*;
import hr.fer.progi.projekt.hotel.service.AccommodatingUnitService;
import hr.fer.progi.projekt.hotel.service.GuestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GuestControllerTest {

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLYWxhbWFya28iLCJyb2xlIjoiUk9MRV9HVUVTVCIsImV4cCI6MTY3NDQzMTcwMn0.f9ZUxH8ShdRzG0CN_FCQvcez-8eVdeSz6cCSTp_tyecvkUrfSlCfo9KWfBIKtwFwjy9im0HSAanMP7Ojo0Q0ng";

    @Autowired
    MockMvc mvc;

    @MockBean
    GuestService guestService;

    @MockBean
    AccommodatingUnitService accommodatingUnitService;

    @Test
    public void reservationInfoTest() throws Exception {
        Date start = new Date(System.currentTimeMillis());
        Date end = new Date(System.currentTimeMillis()+24000000);
        GuestReservation guestResrvation = new GuestReservation(
                new Reservation(start, end, 100F, new Guest("Kalamarko", "12345"))
                , new Guest("Kalamarko", "12345")
                , new AccommodatingUnit("101", 1, new UnitType("JEDNOKREVETNA", 100L)));
        given(guestService.getGuestReservationByUsername("Kalamarko")).willReturn(Optional.of(guestResrvation));

        mvc.perform(get("/myreservation").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("start").value(start.toString()))
                .andExpect(jsonPath("end").value(end.toString()))
                .andExpect(jsonPath("accommodatingUnit").value("101"))
                .andExpect(jsonPath("services", hasSize(0)));
    }

}
