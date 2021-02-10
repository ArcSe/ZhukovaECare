package com.javaschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract")
public class Contract extends AbstractModel{


    @Column(name = "number",  unique = true)
    private String number;

    @Column(name = "isLocked")
    private boolean isLocked = false;

    @Column(name = "isLockedByAdmin")
    private boolean isLockedByAdmin = false;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "tarrif_id")
    private Tariff tariff;

    @ManyToOne
    @JoinColumn(name = "client_id",
                referencedColumnName ="id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "option_contract",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> options;

}

