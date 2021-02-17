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
public class Option implements Serializable {
    @Id
    private long id;
    private String name;
    private int price;
    private int serviceCost;
    private Set<Long> mandatoryOptions = new HashSet<>();
    private Set<Long> bannedOptions = new HashSet<>();

    public Option(String name, int price, int serviceCost) {
        this.name = name;
        this.price = price;
        this.serviceCost = serviceCost;
    }
}
