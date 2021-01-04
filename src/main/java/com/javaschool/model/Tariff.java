package com.javaschool.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@Table(name = "tariffs")
public class Tariff extends AbstractModel{

    @Column(name = "name", nullable=false)
    private String name;

    @ManyToMany(mappedBy = "tariffs")
    private Set<Option> options;

}
