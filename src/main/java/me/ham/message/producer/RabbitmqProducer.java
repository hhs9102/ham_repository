package me.ham.message.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToMessageQueue(String name){
        rabbitTemplate.convertAndSend("q.hsham", "Hello "+name);
    }
}
