package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unit_type")
public class UnitType {

    public UnitType() {
    }

    public UnitType(String type, Long iznos) {
        this.type = type;
        this.iznos = iznos;
    }

    @Id
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "iznos", nullable = false)
    private Long iznos;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getIznos() {
        return iznos;
    }

    public void setIznos(Long iznos) {
        this.iznos = iznos;
    }
}
