package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "refill_request")
public class RefillRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "accommodating_unit_id", nullable = false)
    private AccommodatingUnit accommodatingUnit;

    public AccommodatingUnit getAccommodatingUnit() {
        return accommodatingUnit;
    }

    public void setAccommodatingUnit(AccommodatingUnit accommodatingUnit) {
        this.accommodatingUnit = accommodatingUnit;
    }

    public Long getId() {
        return id;
    }



}
