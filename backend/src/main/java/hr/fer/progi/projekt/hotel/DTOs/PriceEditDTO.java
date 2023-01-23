package hr.fer.progi.projekt.hotel.DTOs;

public class PriceEditDTO {

    private String type;

    private Long price;

    public PriceEditDTO() {
    }

    public PriceEditDTO(String type, Long price) {
        this.type = type;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
