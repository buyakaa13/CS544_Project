package org.example.coffeeshop.JMS;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    public String sendMessage(String queueName, JSONObject message){
        jmsTemplate.convertAndSend(queueName, message.toString());
        return "Message successfully sent.";
    }
}
