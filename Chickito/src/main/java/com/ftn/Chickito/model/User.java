package com.ftn.Chickito.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ftn.Chickito.model.enums.GenderType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@Entity
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private GenderType gender;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "last_password_reset_date")
    private Timestamp lastPasswordResetDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Sector sector;

    private Integer vacationDaysPerYear;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<VacationDay> vacationDays;

    private int oldVacationDays = 0;
    private int availableVacationDays = 0;

    public User(User u) {
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.email = u.getEmail();
        this.phoneNumber = u.getPhoneNumber();
        this.gender = u.getGender();
        this.deleted = u.isDeleted();
        this.active = u.isActive();
        this.lastPasswordResetDate = u.getLastPasswordResetDate();
        this.role = u.getRole();
        this.sector = u.getSector();
        this.availableVacationDays = u.getAvailableVacationDays();
        this.vacationDays = u.getVacationDays();
        this.oldVacationDays = u.getOldVacationDays();
    }

    public User() {

    }


    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<Role> collection = new ArrayList<>();
        collection.add(this.role);
        return collection;
    }


    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
