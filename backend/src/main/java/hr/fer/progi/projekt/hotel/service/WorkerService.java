package hr.fer.progi.projekt.hotel.service;

import hr.fer.progi.projekt.hotel.domain.Worker;

import java.util.List;
import java.util.Optional;

public interface WorkerService {

    List<Worker> listAllWorkers();

    Worker createWorker(Worker Worker);

    Optional<Worker> findByUsername(String username);

    Optional<Worker> findById(Long workerId);

    Worker deleteWorker(Long workerId);

    Worker fetch(Long workerId);

    Worker fetch(String username);

    void changeRole(String username, String role);

    Optional<Worker> findByOib(String oib);
}
