package com.javaschool.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "options")
public class Option {

    @Id
    private long id;

    private int price;

    private int serviceCost;

    public Option() {
    }

    public Option(long id, int price, int serviceCost) {
        this.id = id;
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

    @Id
    public long getId() {
        return id;
    }
}
