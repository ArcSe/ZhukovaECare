package com.javaschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Table(name = "contracts")
public class Contract extends AbstractModel{


    @Column(name = "number", nullable=false, unique = true)
    private int number;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "tarrif_id")
    private Tariff tariff;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_contract",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> options;

}
