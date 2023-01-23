package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.domain.Worker;
import hr.fer.progi.projekt.hotel.service.WorkerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
@SpringBootTest
public class WorkerServiceTest {
    @Autowired
    WorkerService workerService;

    @Test
    public void createWorkerTest(){
        Assertions.assertThrows(NullPointerException.class, () -> workerService.createWorker(null));

        Worker w1 = new Worker("Test1", "Test1", "10201020101", "Test1",
                "098123456", "TEST", "TEST", "ROLE_RECEPTIONIST",
                new Date(System.currentTimeMillis()));

        workerService.createWorker(w1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> workerService.createWorker(w1));

        workerService.deleteWorker(workerService.findByUsername("TEST").get().getId());
        Assertions.assertTrue(workerService.findByUsername("TEST").isEmpty());
    }

}
