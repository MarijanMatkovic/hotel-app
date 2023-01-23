package hr.fer.progi.projekt.hotel.DTOs;

public class HotelInfoDTO {

    private String address;

    private String hotelName;

    private String fax;

    private Long oib;

    private String email;

    public HotelInfoDTO() {
    }

    public HotelInfoDTO(String address, String hotelName) {
        this.address = address;
        this.hotelName = hotelName;
    }

    public HotelInfoDTO(String address, String hotelName, String fax, Long oib, String email) {
        this.address = address;
        this.hotelName = hotelName;
        this.fax = fax;
        this.oib = oib;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getOib() {
        return oib;
    }

    public void setOib(Long oib) {
        this.oib = oib;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
