package me.ham.message.consumer;

import me.ham.user.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqConsumer {

    @RabbitListener(queues = "q.hsham")
    public void getFromMessageQueue(String message) {
        System.out.println("Consuming :::"+message);
    }

    @RabbitListener(queues = "q.person")
    public void getPersonFromMessageQueue(User user) {
        System.out.println("Consuming :::"+user.toString());
    }
}
