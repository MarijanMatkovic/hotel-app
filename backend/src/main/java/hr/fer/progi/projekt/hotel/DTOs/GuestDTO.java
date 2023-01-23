package hr.fer.progi.projekt.hotel.DTOs;

public class GuestDTO {

    private String name;

    private String surname;

    private String oib;

    private String address;

    private String phoneNumber;

    private String country;

    private String username;
    private String accommodatingUnitName;

    public GuestDTO() {}

    public GuestDTO(String name, String surname, String oib, String address, String  phoneNumber, String username,
                          String country, String accommodatingUnitName) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.country = country;
        this.accommodatingUnitName = accommodatingUnitName;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getOib() {
        return oib;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getAccommodatingUnitName() {
        return accommodatingUnitName;
    }
}
