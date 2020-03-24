package me.ham.message.producer;

import me.ham.user.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RabbitmqProducer {
    private final Random random = new Random();
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToMessageQueue(String name){
        rabbitTemplate.convertAndSend("q.hsham", name);
    }

    public void sendPersonToMessageQueue(String name){
        rabbitTemplate.convertAndSend("q.person", User.builder()
                .name(name)
                .age(getRandomAge())
                .build());
    }

    public int getRandomAge(){
        return random.nextInt(60)+20;
    }
}
