package com.javaschool.jms;

import com.javaschool.dto.OptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
public class JmsProducer {
    final
    JmsTemplate jmsTemplate;

    @Value("notificationsQueue")
    String queue;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(String command){
        jmsTemplate.convertAndSend(queue, command);
    }
}