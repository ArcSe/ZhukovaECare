package com.javaschool.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Counter {
    @Id
    private String name = "contract";

    private int value = 1;

}
