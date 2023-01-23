package hr.fer.progi.projekt.hotel.DTOs;

import java.util.Map;

public class BillDTO {

    private Long brojRezervacije;

    private Map<String, Float> iznos;

    private float price;

    public BillDTO(Long brojRezervacije, float price) {
        this.brojRezervacije = brojRezervacije;
        this.price = price;
    }

    public BillDTO() {}

    public BillDTO(Long brojRezervacije, Map<String, Float> iznos) {
        this.brojRezervacije = brojRezervacije;
        this.iznos = iznos;
        this.price = (float) iznos.values().stream().mapToDouble(t -> t).sum();
    }

    public Long getBrojRezervacije() {
        return brojRezervacije;
    }

    public void setBrojRezervacije(Long brojRezervacije) {
        this.brojRezervacije = brojRezervacije;
    }

    public Map<String, Float> getIznos() {
        return iznos;
    }

    public void setIznos(Map<String, Float> iznos) {
        this.iznos = iznos;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
