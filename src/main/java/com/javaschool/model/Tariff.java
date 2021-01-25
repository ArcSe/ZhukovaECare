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
@Table(name = "tariff")
public class Tariff extends AbstractModel{

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "price")
    private int price;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tariff_option",
            joinColumns = @JoinColumn(name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private Set<Option> options;

}
