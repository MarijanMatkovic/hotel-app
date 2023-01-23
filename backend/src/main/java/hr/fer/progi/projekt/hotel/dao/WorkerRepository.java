package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Transactional
    @Modifying
    @Query("update Worker w set w.role = ?2 WHERE w.username = ?1")
    void setRole(@NonNull String username, String role);

    Optional<Worker> findByUsername(String username);

    List<Worker> findByOrderById();

    Optional<Worker> findByOib(String oib);
}
