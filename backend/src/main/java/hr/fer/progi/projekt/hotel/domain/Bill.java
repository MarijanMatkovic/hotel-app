package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(name = "broj_racuna", nullable = false)
    private Long brojRacuna;

    @Column(name = "iznos", nullable = false)
    private Long iznos;

    public Bill() {
    }

    public Bill(Long brojRacuna, Long iznos) {
        this.brojRacuna = brojRacuna;
        this.iznos = iznos;
    }

    public Long getBrojRacuna() {
        return brojRacuna;
    }

    public void setBrojRacuna(Long brojRacuna) {
        this.brojRacuna = brojRacuna;
    }

    public Long getIznos() {
        return iznos;
    }

    public void setIznos(Long iznos) {
        this.iznos = iznos;
    }
}
