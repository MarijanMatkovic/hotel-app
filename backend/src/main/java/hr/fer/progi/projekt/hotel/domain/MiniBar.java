package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "mini_bar")
public class MiniBar {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "amount", nullable = false)
    private Float amount;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
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
