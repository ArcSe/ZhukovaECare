package com.javaschool.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    public Option(long id, String name, int price, int serviceCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceCost = serviceCost;
    }

}
