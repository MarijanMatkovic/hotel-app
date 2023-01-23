package hr.fer.progi.projekt.hotel.rest;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;
import hr.fer.progi.projekt.hotel.domain.Hotel;
import hr.fer.progi.projekt.hotel.domain.UnitType;
import hr.fer.progi.projekt.hotel.domain.Worker;
import hr.fer.progi.projekt.hotel.service.AccommodatingUnitService;
import hr.fer.progi.projekt.hotel.service.HotelService;
import hr.fer.progi.projekt.hotel.service.WorkerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OwnerControllerTest {

    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJEb21hIiwicm9sZSI6IlJPTEVfT1dORVIiLCJleHAiOjE2NzQ0MjkxNzZ9.tuqP_i_jQtFdoLUWCqfqVrRHieJXyt0s5R_LBHXZMLhK8kTD51Um8hiUMx86cq5aGVaixuFfLbZxYqqCDgfoAg";

    @Autowired
    MockMvc mvc;

    @MockBean
    HotelService hotelService;

    @MockBean
    AccommodatingUnitService accommodatingUnitService;

    @MockBean
    WorkerService workerService;


    @Test
    public void hotelInfoTest() throws Exception {
        List<Hotel> hotelInfo = new ArrayList<>();
        hotelInfo.add(new Hotel("TEST", "TEST", "TEST", 12345678911L, "TEST"));
        given(hotelService.listAll()).willReturn(hotelInfo);

        mvc.perform(get("/hotel/info").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("hotelName").value("TEST"))
                .andExpect(jsonPath("oib").value("12345678911"));
    }

    @Test
    public void allWorkersTest() throws Exception {
        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("TEST", "TEST", "12345678911", "TEST", "0981564256", "TEST", "1234", "ROLE_RECEPTIONIST", new Date(System.currentTimeMillis())));
        workers.add(new Worker("TEST", "TEST", "12345678912", "TEST", "0981564256", "TEST", "1234", "ROLE_RECEPTIONIST", new Date(System.currentTimeMillis())));
        workers.add(new Worker("TEST", "TEST", "12345678913", "TEST", "0981564256", "TEST", "1234", "ROLE_HOUSEKEEPER", new Date(System.currentTimeMillis())));
        workers.add(new Worker("TEST", "TEST", "12345678914", "TEST", "0981564256", "TEST", "1234", "ROLE_OWNER", new Date(System.currentTimeMillis())));
        given(workerService.listAllWorkers()).willReturn(workers);

        mvc.perform(get("/hotel/info/workers").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].oib").value("12345678911"))
                .andExpect(jsonPath("$[1].oib").value("12345678912"))
                .andExpect(jsonPath("$[2].oib").value("12345678913"));
    }

    @Test
    public void allUnitsTest() throws Exception {
        List<AccommodatingUnit> aUnits = new ArrayList<>();
        aUnits.add(new AccommodatingUnit("101", 4, new UnitType("OBITELJSKA", 100L)));
        aUnits.add(new AccommodatingUnit("102", 1, new UnitType("JEDNOKREVETNA", 20L)));
        aUnits.add(new AccommodatingUnit("201", 2, new UnitType("DVOKREVETNA", 45L)));

        given(accommodatingUnitService.listAll()).willReturn(aUnits);

        mvc.perform(get("/hotel/info/units").header("Authorization", token).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("101"))
                .andExpect(jsonPath("$[1].capacity").value("1"))
                .andExpect(jsonPath("$[2].name").value("201"));
    }

}
