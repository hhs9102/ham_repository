package me.ham.async.coffee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import sun.reflect.generics.repository.ConstructorRepository;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@Component
@RequiredArgsConstructor
public class CoffeeComponent implements CoffeeService{

    @Autowired
    private final CoffeeRespository coffeeRespository;

    Executor executor = Executors.newFixedThreadPool(10);

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public int getPrice(String name) {
        log.info("비동기 조회 시작.");
        return coffeeRespository.getPriceByName(name);
    }

    @Override
    public CompletableFuture<Integer> getPriceAsync(String name) {
        CompletableFuture<Integer> future = new CompletableFuture<>();

        log.info("비동기 조회 시작");

//        new Thread(() -> {
//            log.info("getPriceAsync의 Thread 시작");
//            Integer price = coffeeRespository.getPriceByName(name);
//            future.complete(price);
//        }).start();
//        return future;
        return CompletableFuture.supplyAsync(()-> {
            log.info("supplyAsync 시");
            return coffeeRespository.getPriceByName(name);
        }, threadPoolTaskExecutor);
    }

    @Override
    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return CompletableFuture.supplyAsync(()->{
            return (int)(price * 0.09);
        }, threadPoolTaskExecutor);
    }
}
