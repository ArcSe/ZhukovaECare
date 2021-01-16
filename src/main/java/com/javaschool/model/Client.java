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

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "passport")
    private int passport;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    private boolean active;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<Contract> contracts;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "client_role", joinColumns = @JoinColumn(name = "client_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

}
