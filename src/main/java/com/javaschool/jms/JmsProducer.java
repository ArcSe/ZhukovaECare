package com.javaschool.jms;

import com.javaschool.dto.OptionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsProducer {
    final
    JmsTemplate jmsTemplate;

    @Value("notificationsQueue")
    String queue;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(OptionDto option){
        jmsTemplate.convertAndSend(queue, option);
    }
}