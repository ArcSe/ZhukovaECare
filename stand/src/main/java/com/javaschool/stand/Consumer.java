package com.javaschool.stand;


import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;


@MessageDriven(name = "notificationsQueue", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "notificationsQueue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class Consumer implements MessageListener {

    @EJB
    private TariffBean tariffBean;

    private final static Logger LOGGER = Logger.getLogger(Consumer.class.toString());

    public void onMessage(Message rcvMessage) {
        LOGGER.fine(RestController.getTariffs().toString());
            tariffBean.update();
    }
}