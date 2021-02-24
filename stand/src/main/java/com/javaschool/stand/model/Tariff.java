package com.javaschool.stand.model;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Tariff implements Serializable {
    @Id
    private long id;
    private String name;
    private int price = 0;
    private int serviceCost = 0;
    private Set<Option> options = new HashSet<>();


    public Tariff(String name, int price, int serviceCost) {
        this.name = name;
        this.price = price;
        this.serviceCost = serviceCost;
    }
}
