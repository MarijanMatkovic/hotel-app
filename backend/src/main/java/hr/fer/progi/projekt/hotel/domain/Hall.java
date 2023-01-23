package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "name", nullable = false)
    private String name;

    public Long getId() {
        return id;
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
}
