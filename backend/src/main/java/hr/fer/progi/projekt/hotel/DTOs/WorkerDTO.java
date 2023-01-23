package hr.fer.progi.projekt.hotel.DTOs;


public class WorkerDTO {

    private String name;
    private String surname;
    private String oib;
    private String address;
    private String phoneNumber;
    private String username;
    private String password;
    private String role;
    private String dateOfHire;
    
    public WorkerDTO() {
    }

    public WorkerDTO(String name, String surname, String oib, String address, String phoneNumber, String username, String password, String role, String dateOfHire) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.role = role;
        this.dateOfHire = dateOfHire;
    }

    public WorkerDTO(String name, String surname, String oib, String address, String phoneNumber, String username, String role, String dateOfHire) {
        this(name, surname, oib, address, phoneNumber, username,null , role, dateOfHire);
    }

    public String getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire = dateOfHire.split("T")[0];
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
