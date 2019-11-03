package me.ham.async.pet;


import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

public interface PetService {

    String syncronizedAndBlocking();
    String asyncronizedAndBlocking() throws ExecutionException, InterruptedException;
    String asyncronizedAndNonBlocking() throws ExecutionException, InterruptedException;

}
