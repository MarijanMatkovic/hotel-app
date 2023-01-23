package hr.fer.progi.projekt.hotel.service.impl;

import hr.fer.progi.projekt.hotel.dao.WorkerRepository;
import hr.fer.progi.projekt.hotel.domain.Worker;
import hr.fer.progi.projekt.hotel.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceJpa implements WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    @Override
    public List<Worker> listAllWorkers() {
        return workerRepository.findByOrderById();
    }

    @Override
    public Worker createWorker(Worker worker) {
        Assert.isNull(worker.getId(), "ID radnika treba biti null, a ne: " + worker.getId());
        Assert.notNull(worker.getUsername(), "Username mora postojati");
        Assert.notNull(worker.getPassword(), "Lozinka mora postojati");
        Assert.isTrue(workerRepository.findByUsername(worker.getUsername()).isEmpty(),
                "Radnik s korisničkim imenom" + worker.getUsername() + " već postoji");
        return workerRepository.save(worker);
    }

    @Override
    public Optional<Worker> findByUsername(String username) {
        Assert.notNull(username, "Username se mora navesti");
        return workerRepository.findByUsername(username);
    }

    @Override
    public Optional<Worker> findById(Long workerId) {
        Assert.notNull(workerId, "ID se treba postaviti");
        return workerRepository.findById(workerId);
    }

    @Override
    public Worker deleteWorker(Long workerId) {
        Worker worker = fetch(workerId);
        workerRepository.delete(worker);
        return worker;
    }

    @Override
    public Worker fetch(Long workerId) {
        return findById(workerId).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    @Override
    public Worker fetch(String username) {
        return findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException()
        );
    }

    @Override
    public void changeRole(String username, String role) {
        workerRepository.setRole(username,role);
    }

    @Override
    public Optional<Worker> findByOib(String oib) {
        return workerRepository.findByOib(oib);
    }

}
