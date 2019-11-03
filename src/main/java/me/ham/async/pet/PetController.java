package me.ham.async.pet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/pet")
@Slf4j
public class PetController {

    @Autowired
    PetService petService;

    @GetMapping("/sync/block")
    public String syncronizedAndBlocking(){
        return petService.syncronizedAndBlocking();
    }

    @GetMapping("/async/block")
    public String asyncronizedAndBlocking() throws ExecutionException, InterruptedException {
        return petService.asyncronizedAndBlocking();
    }

    @GetMapping("/async/nonblock")
    public String asyncronizedAndNonBlocking() throws ExecutionException, InterruptedException {
        return petService.asyncronizedAndNonBlocking();
    }

}
