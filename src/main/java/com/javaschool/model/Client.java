package com.javaschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "clients", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "surname", nullable=false)
    private String surname;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "passport", nullable=false)
    private int passport;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password", nullable=false)
    private String password;

    public Client(long id, String name, String surname, Date birthday, int passport, String email, String address, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.passport = passport;
        this.email = email;
        this.address = address;
        this.password = password;
    }

}
