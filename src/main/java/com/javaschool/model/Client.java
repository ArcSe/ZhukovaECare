package com.javaschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class Client extends AbstractModel {

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

    @OneToMany(mappedBy = "client")
    private Set<Contract> contracts;

}
