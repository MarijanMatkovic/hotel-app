package hr.fer.progi.projekt.hotel.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "worker")
public class Worker extends HotelUser {

    public Worker() {}

    public Worker(String name, String surname, String oib, String address, String phoneNumber, String username, String password, String role, Date dateOfHire) {
        super(name, surname, oib, address, phoneNumber, username, password);
        this.role = role;
        this.dateOfHire = dateOfHire;
    }

    @Column(name = "date_of_hire", nullable = false)
    private Date dateOfHire;

    @Column(name="role", nullable = false)
    private String role;


    public Date getDateOfHire() {
        return dateOfHire;
    }

    public void setDateOfHire(Date dateOfHire) {
        this.dateOfHire = dateOfHire;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }
}
