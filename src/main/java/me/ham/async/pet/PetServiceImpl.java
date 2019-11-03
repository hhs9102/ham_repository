package me.ham.async.pet;

import lombok.extern.slf4j.Slf4j;
import me.ham.async.pet.aop.PetAspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetAspect petAspect;

    RestTemplate restTemplate;

    public PetServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public String syncronizedAndBlocking() {
        String result1 = restTemplate.getForObject("http://localhost:8080/connect/waitting/3000", String.class);
        String result2 = restTemplate.getForObject("http://localhost:8080/connect/waitting/2000", String.class);
        log.info("[{}] : syncronizedAndBlocking is executed", Thread.currentThread().getName());
        return result1 +"/"+result2;
    }

    @Override
    public String asyncronizedAndBlocking() throws ExecutionException, InterruptedException {
        String result = "";
        log.info("[{}] : request result1", Thread.currentThread().getName());
        CompletableFuture<String> result1 = asyncronizedAndBlocking(3000);
        log.info("[{}] : request result2", Thread.currentThread().getName());
        CompletableFuture<String> result2 = asyncronizedAndBlocking(2000);
        log.info("[{}] : reponse result1 -> result1 블로킹 상태", Thread.currentThread().getName());
        result = result1.get();
        log.info("[{}] : reponse result1 -> result2 블로킹 상태", Thread.currentThread().getName());
        result += result2.get();
        return result;
    }

    public CompletableFuture<String> asyncronizedAndBlocking(long ms){
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(()->{
            future.complete(restTemplate.getForObject("http://localhost:8080/connect/waitting/"+ms, String.class));
            log.info(Thread.currentThread().getName());
        }).start();
        log.info("[{}] : asyncronizedAndBlocking is executed", Thread.currentThread().getName());
        return future;
    }

    @Override
    public String asyncronizedAndNonBlocking(){
        Executor executor = Executors.newFixedThreadPool(1);
        CompletableFuture<Void> result1 = asyncronizedAndNonBlocking(3000, executor).thenAcceptAsync(
                result -> {
                    log.info("[{}] : accepted result1 : "+result, Thread.currentThread().getName());
                },executor);
        log.info("[{}] : request result1", Thread.currentThread().getName());

        CompletableFuture<Void> result2 = asyncronizedAndNonBlocking(2000, executor).thenAcceptAsync(
                result -> {
                    log.info("[{}] : accepted result2 : "+result, Thread.currentThread().getName());
                },executor);
        log.info("[{}] : request result2", Thread.currentThread().getName());
        log.info("[{}] : END :: asyncronizedAndNonBlocking", Thread.currentThread().getName());
        return null;
    }

    public CompletableFuture<String> asyncronizedAndNonBlocking(long ms, Executor executor){
        return CompletableFuture.supplyAsync(() -> {
            log.info("[{}] : asyncronizedAndNonBlocking", Thread.currentThread().getName());
            return restTemplate.getForObject("http://localhost:8080/connect/waitting/"+ms, String.class);
        }, executor);
    }

}
