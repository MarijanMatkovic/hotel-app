package hr.fer.progi.projekt.hotel.DTOs;

public class AccommodatingUnitDTO {

    private String unitType;

    private int capacity;

    private String name;

    private double price;

    public AccommodatingUnitDTO() {
    }

    public AccommodatingUnitDTO(String unitType, int capacity, String name) {
        this.unitType = unitType;
        this.capacity = capacity;
        this.name = name;
    }

    public AccommodatingUnitDTO(String unitType, int capacity, String name, double price) {
        this.unitType = unitType;
        this.capacity = capacity;
        this.name = name;
        this.price = price;
    }

    public String getUnitType() {
        return unitType;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccommodatingUnitDTO that = (AccommodatingUnitDTO) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
