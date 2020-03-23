package me.ham.message;

import me.ham.message.producer.RabbitmqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RabbitmqController {

    private final RabbitmqProducer rabbitmqProducer;

    @Autowired
    public RabbitmqController(RabbitmqProducer rabbitmqProducer) {
        this.rabbitmqProducer = rabbitmqProducer;
    }

    @PostMapping(value = "/rabbit/{name}")
    public ResponseEntity<Void> sendToMessageQueue(@PathVariable String name){
        rabbitmqProducer.sendToMessageQueue(name);
        return ResponseEntity.ok().build();
    }
}
