package com.javaschool.stand;

import com.javaschool.stand.model.Tariff;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("tariffBean")
//@Stateless
@ApplicationScoped
public class TariffBean implements Serializable {

    @Inject
    private RestController rest;

    @Inject
    @Push(channel = "push")
    PushContext push;


    private List<Tariff> tariffs;

    public void setTariffs() {
        this.tariffs = rest.getTariffs();
    }

    public List<Tariff> getTariffs() {
        return rest.getTariffs();
    }

    public void update() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        push.send("updateTariffs");
    }
}