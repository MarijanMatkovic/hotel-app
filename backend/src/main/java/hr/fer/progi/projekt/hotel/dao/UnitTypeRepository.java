package hr.fer.progi.projekt.hotel.dao;

import hr.fer.progi.projekt.hotel.domain.UnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

public interface UnitTypeRepository extends JpaRepository<UnitType, String> {
    @Transactional
    @Modifying
    @Query("update UnitType u set u.iznos = ?1 where u.type like ?2")
    void updateIznosByTypeLike(@NonNull Long iznos, @NonNull String type);
    UnitType findByType(String type);
}