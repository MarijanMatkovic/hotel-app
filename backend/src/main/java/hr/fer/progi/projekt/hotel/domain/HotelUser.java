package hr.fer.progi.projekt.hotel.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "hotel_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class HotelUser implements UserDetails {

    public HotelUser() {
    }

    public HotelUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public HotelUser(String name, String surname, String oib, String address, String phoneNumber, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.oib = oib;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "surname", length = 50)
    private String surname;

    @Column(name = "oib", unique = true)
    @Size(min = 11, max = 11)
    private String oib;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "phone_number", unique = true, length = 20)
    private String phoneNumber;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 150)
    private String password;


    public Long getId() {
        return id;
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

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_GUEST"));
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
