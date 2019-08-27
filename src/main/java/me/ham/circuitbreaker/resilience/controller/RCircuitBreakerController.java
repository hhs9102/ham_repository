package me.ham.circuitbreaker.resilience.controller;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import me.ham.circuitbreaker.resilience.controller.connector.PetClinicConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;

@RestController
@RequestMapping("/resilience")
public class RCircuitBreakerController {

    Logger logger = LoggerFactory.getLogger(RCircuitBreakerController.class);

    //@Autowired
    PetClinicConnector petClinicConnector;

    @GetMapping("/consumer")
    public String Consumer(@RequestParam String path){
        // Create a custom configuration for a CircuitBreaker
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(100))
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .recordExceptions(IOException.class, TimeoutException.class)
                .build();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("hsham");

        Supplier<String> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, ()->callApi(path));

        circuitBreaker.getEventPublisher()
                .onSuccess(event -> logger.info("onSuccess is called"))
                .onError(event -> logger.info("onError is called"))
                .onIgnoredError(event -> logger.info("onIgnoredError is called"))
                .onReset(event -> logger.info("onReset is called"))
                .onStateTransition(event -> logger.info("onStateTransition is called"));

        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> {
                    System.out.println("fallback called!!!!!!!!!!");
                    return "Hello from Recovery";
                }).get();
        logger.info("result : "+result);
        return result;
    }

    private String callApi(String path){
        System.out.println("log :: "+path);

        ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/" + path, String.class);
        if(entity.getStatusCode() == HttpStatus.OK){
            System.out.println("result :: "+entity.getBody());
            return entity.getBody();
        }else{
            System.out.println("HttpStatus is not OK");
        }

        throw new RuntimeException("supplier is not OK");
    }

    private String fallback(String path){
        System.out.println("called fallback method");
        return "hello fallback";
    }

    @GetMapping(value = "/sucess")
    public String sucess(){
        return petClinicConnector.sucess();
    }
    @GetMapping(value = "/failure")
    public String failure(){
        return petClinicConnector.failure();
    }
    @GetMapping(value = "/waitting/{ms}")
    public String waitting(@PathVariable("ms") long ms){
        return petClinicConnector.waitting(ms);
    }

    @GetMapping(value = "/configure/waitting/{ms}")
    public String configureWaitting(@PathVariable("ms") long ms){
        return petClinicConnector.configureWaitting(ms);
    }
}
