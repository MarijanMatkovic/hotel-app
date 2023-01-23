package hr.fer.progi.projekt.hotel.DTOs;

import hr.fer.progi.projekt.hotel.domain.AccommodatingUnit;

public class UserLoginDTO {

    private String name;

    private String surname;

    private String oib;

    private String address;

    private String phoneNumber;

    private String username;

    private String password;

    private String country;

    private AccommodatingUnit accommodatingUnit;

    public UserLoginDTO() {}


    public UserLoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserLoginDTO(String name, String surname, String oib, String address, String  phoneNumber, String username,
                        String password, String country, AccommodatingUnit accommodatingUnit) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.country = country;
        this.accommodatingUnit = accommodatingUnit;
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

    public AccommodatingUnit getAccommodatingUnit() {
        return accommodatingUnit;
    }
}

