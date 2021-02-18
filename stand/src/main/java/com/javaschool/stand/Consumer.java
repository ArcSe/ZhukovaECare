package com.javaschool.stand;


import com.javaschool.stand.model.Tariff;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

//@Named("consumer")
@MessageDriven(name = "notificationsQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "notificationsQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class Consumer implements MessageListener {

//    @Inject
//    private TariffBean tariffBean;

    @Inject
    private RestController rest;

    @Inject
    @Push(channel = "push")
    PushContext push;


    private List<Tariff> tariffs;

    @PostConstruct
    public void setTariffs() {
        this.tariffs = rest.getTariffs();
    }

    public List<Tariff> getTariffs() {
        return this.tariffs;
    }

    public void update() {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        push.send("updateTariffs");
    }

    private final static Logger LOGGER = Logger.getLogger(Consumer.class.toString());

    public void onMessage(Message rcvMessage) {
        setTariffs();
        update();
        System.out.println(rcvMessage);
    }
}