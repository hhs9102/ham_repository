package me.ham.async.coffee;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.naming.Name;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
@RunWith(SpringRunner.class)
@ContextConfiguration(classes =
        {CoffeeComponent.class
        ,CoffeeRespository.class
        ,TaskConfig.class}
        )
public class CoffeeComponentTest {

    @Autowired
    CoffeeComponent coffeeComponent;

    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void getPrice() {
        int expectedPrice = 4100;
        Assert.assertEquals(expectedPrice, coffeeComponent.getPrice("americano"));
    }

    @Test
    public void getPriceAsync() {
        int expectedPrice = 5100;
        log.info("11111111");
        CompletableFuture<Integer> futurePrice = coffeeComponent.getPriceAsync("latte");
        log.info("22222222");
        int resultPrice = futurePrice.join();
        log.info("33333333");   //Async + 블로킹 조합으로 join이 호출되고 값을 받을때까지는 해당 코드가 실행되지않음.
        Assert.assertEquals(expectedPrice, resultPrice);
    }

    @Test
    public void getPriceAsyncSupplier() {
        Integer expectedPrice = 4700;

        CompletableFuture<Void> future = coffeeComponent
                .getPriceAsync("choco")
                .thenApplyAsync(p -> {
                    log.info("thenApplyAsync : " + p);
                    return p+100;
                }, threadPoolTaskExecutor)
                .thenAccept(p -> {
                    log.info("callback price : " + p);
                    Assert.assertEquals(expectedPrice, p);
                });

        log.info("이게 먼저 출력되면 논블로킹");
        Assert.assertNull(future.join());
    }

    @Test
    public void getDiscountPriceAysnc(){
        Integer expectedPrice = (int)(4100 * 0.09);

        CompletableFuture<Integer> future = coffeeComponent
                .getPriceAsync("americano")
                ;

        Integer discountedPrice = future.thenCompose(result ->
           coffeeComponent.getDiscountPriceAsync(result)).join();

        Integer discountedLatte = coffeeComponent
                .getPriceAsync("latte")
                .thenApply(p -> (int)(p*0.09)).join();
        System.out.println(discountedLatte);


        log.info(expectedPrice + "expectedPrice");
        Assert.assertEquals(expectedPrice, discountedPrice);
    }
}
