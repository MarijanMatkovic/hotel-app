package hr.fer.progi.projekt.hotel.DTOs;

public class UpdateGuestDTO {

    private String name;

    private String surname;

    private String oib;

    private String address;

    private String phoneNumber;

    private String password;

    private String country;

    private String username;

    public UpdateGuestDTO() {}

    public UpdateGuestDTO(String name, String surname, String oib, String address, String  phoneNumber, String username,
                        String password, String country) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

}
