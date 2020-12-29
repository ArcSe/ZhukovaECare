package com.javaschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "price", nullable=false)
    private int price;

    @Column(name = "serviceCost")
    private int serviceCost;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "contract_option",
            joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    private Set<Contract> contracts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tariff_option",
            joinColumns = @JoinColumn(name = "option_id"),
            inverseJoinColumns = @JoinColumn(name = "tariff_id"))
    private Set<Option> options;

    public Option(long id, String name, int price, int serviceCost,
                  Set<Contract> contracts, Set<Option> options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceCost = serviceCost;
        this.contracts = contracts;
        this.options = options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return id == option.id && price == option.price && serviceCost == option.serviceCost && Objects.equals(name, option.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, serviceCost);
    }
}
