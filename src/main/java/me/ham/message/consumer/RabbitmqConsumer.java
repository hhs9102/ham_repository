package me.ham.message.consumer;

import me.ham.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqConsumer {

//    @RabbitListener(queues = "q.hsham")
    public void getFromMessageQueue(String message) {
        printCurrentThreadName();
        System.out.println("Consuming :::"+message);
    }

//    @RabbitListener(queues = "q.person")
    public void getPersonFromMessageQueue(User user) {
        printCurrentThreadName();
        System.out.println("Consuming :::"+user.toString());
    }

    private void printCurrentThreadName(){
        System.out.println("Current thread::::"+Thread.currentThread().getName());
    }
}
