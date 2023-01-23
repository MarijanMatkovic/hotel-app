package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "accommodating_unit")
public class AccommodatingUnit {
    public AccommodatingUnit(String name, Integer capacity, UnitType accommodatingUnitType) {
        this.name = name;
        this.capacity = capacity;
        this.unitType = accommodatingUnitType;
    }

    public AccommodatingUnit() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type", nullable = false)
    private UnitType unitType;

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

}
