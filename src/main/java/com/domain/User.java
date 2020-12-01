package com.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "pesel")
    private String pesel;

    @Builder.Default
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "AppUsersRoles", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = @JoinColumn(name = "userRoleID"))
    private Set<UserRole> userRolesList = new HashSet<>();

    @Builder.Default
    @OneToMany(
            targetEntity =  Bookings.class,
            mappedBy = "USER_ID",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Bookings> toolsBookings = new ArrayList<>();

    public User() {
        super();
    }

    public User(Long id, String name, String surname, String email, String password, String phone, String pesel, Set<UserRole> userRolesList) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.pesel = pesel;
        this.userRolesList = userRolesList;
    }

    public User(Long id, String name, String surname, String email, String password, String phone, String pesel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.pesel = pesel;
    }
}
