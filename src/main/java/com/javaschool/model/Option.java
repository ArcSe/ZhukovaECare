package com.javaschool.model;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private int name;

    @Column(name = "price")
    private int price;

    @Column(name = "serviceCost")
    private int serviceCost;

    public Option() {
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public Option(long id, int name, int price, int serviceCost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.serviceCost = serviceCost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(int serviceCost) {
        this.serviceCost = serviceCost;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
