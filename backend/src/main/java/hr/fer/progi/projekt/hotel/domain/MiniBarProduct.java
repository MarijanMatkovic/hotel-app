package hr.fer.progi.projekt.hotel.domain;

import javax.persistence.*;

@Entity
@Table(name = "minibar_product")
public class MiniBarProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private MiniBar product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "refill_request_id", nullable = false)
    private RefillRequest refillRequest;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public RefillRequest getRefillRequest() {
        return refillRequest;
    }

    public void setRefillRequest(RefillRequest refillRequest) {
        this.refillRequest = refillRequest;
    }

    public MiniBar getProduct() {
        return product;
    }

    public void setProduct(MiniBar product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }



}
