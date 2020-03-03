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

import java.util.function.Supplier;

@RestController
@RequestMapping("/resilience")
public class RCircuitBreakerController {

    Logger logger = LoggerFactory.getLogger(RCircuitBreakerController.class);

    @Autowired
    PetClinicConnector petClinicConnector;

    private final String PET_CLINIC_PATH = "http://localhost:8080/connect/";

    @GetMapping("/consumer")
    public String consumer(@RequestParam String path) throws Exception {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.ofDefaults();
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("hsham");
        Supplier<String> decoratedSupplier = CircuitBreaker
                .decorateSupplier(circuitBreaker, ()->callApi(path));

        String result = Try.ofSupplier(decoratedSupplier)
                .recover(throwable -> {
                    System.out.println("fallback called!!!!!!!!!");
                    return "Hello from Recovery";
                }).get();
        logger.info("consumer result :: "+result);

        circuitBreaker.executeCallable(this::sucess);
        return result;
    }

    private String callApi(String path){
        logger.info("call path :: " + path);

        ResponseEntity<String> entity = new RestTemplate().getForEntity(PET_CLINIC_PATH + path, String.class);
        if(entity.getStatusCode() == HttpStatus.OK){
            logger.info("result :: "+entity.getBody());
            return entity.getBody();
        }else{
            logger.info("HttpStatus is not OK");
    }

        throw new RuntimeException("supplier is not OK");
    }

    private String fallback(String path){
        logger.info("called fallback method");
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
    public String waitting(@PathVariable("ms") long ms) throws InterruptedException {
//        return petClinicConnector.waitting(ms);
        System.out.println(String.format("waitting %dms request called ", ms));
        Thread.sleep(ms);
        return String.format("%d ms 후 리턴", ms);
    }

    @GetMapping(value = "/configure/waitting/{ms}")
    public String configureWaitting(@PathVariable("ms") long ms){
        return petClinicConnector.configureWaitting(ms);
    }
}
