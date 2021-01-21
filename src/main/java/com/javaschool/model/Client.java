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
@Table(name = "clients")
public class Client extends AbstractModel {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "passport")
    private int passport;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "client")
    private User user;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Contract> contracts;


}
