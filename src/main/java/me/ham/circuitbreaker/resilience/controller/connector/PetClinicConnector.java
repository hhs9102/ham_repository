package me.ham.circuitbreaker.resilience.controller.connector;


import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@CircuitBreaker(name="petclinic")
@Retry(name="petclinic")
@RateLimiter(name="petclinic")
@Slf4j
@Component
public class PetClinicConnector implements Connector {

    private final String PET_CLINIC_URL = "http://localhost:8080/";
    private final String SUCESS_PATH = "connect/sucess";
    private final String FAIL_PATH = "connect/failure";
    private final String WAIT_PATH = "connect/waitting";

    @Override
    @CircuitBreaker(name = "petclinic", fallbackMethod ="fallback")
    public String sucess() {
        log.info("PetClinicConnector#### called sucess method");
        String result = new RestTemplate().getForEntity(PET_CLINIC_URL+SUCESS_PATH, String.class).getBody();
        log.info(result);
        return result;
    }

    @Override
    @CircuitBreaker(name = "petclinic", fallbackMethod = "fallback")
    public String failure() {
        log.info("PetClinicConnector#### called failure method");
        String result = new RestTemplate().getForEntity(PET_CLINIC_URL+FAIL_PATH, String.class).getBody();
        log.info(result);
        return result;
    }

    @Override
    @CircuitBreaker(name = "petclinic", fallbackMethod ="fallback")
    public String waitting(long ms) {
        log.info("PetClinicConnector#### called waitResponse method");
        String result = new RestTemplate().getForEntity(PET_CLINIC_URL+WAIT_PATH+"/"+ms, String.class).getBody();
        log.info(result);
        return result;
    }

    public String configureWaitting(long ms) {
        // Create a custom configuration for a CircuitBreaker
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofMillis(1000))
                .ringBufferSizeInHalfOpenState(2)
                .ringBufferSizeInClosedState(2)
                .build();

        // Create a CircuitBreakerRegistry with a custom global configuration
        CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);

        // Get a CircuitBreaker from the CircuitBreakerRegistry with the global default configuration
        io.github.resilience4j.circuitbreaker.CircuitBreaker c = circuitBreakerRegistry.circuitBreaker("a");

        log.info("PetClinicConnector#### called waitResponse method");
        String result = c.executeSupplier(() -> new RestTemplate().getForEntity(PET_CLINIC_URL+WAIT_PATH+"/"+ms, String.class).getBody());

        c.getCircuitBreakerConfig().getWaitDurationInOpenState();

        log.info(result);
        return result;
    }

    public String fallback(){
        log.info("#############called fallback method#############");
        return "called fallback method.";
    }

    public String fallback(Exception e){
        log.info("#############called fallback method#############");
        log.info("ERROR################\n"+e.getMessage());
        return "called fallback method.";
    }

    public String fallback(long ms, Exception e){
        log.info("#############called fallback method#############");
        log.info("ERROR################\n"+e.getMessage());
        return "called fallback method.";
    }

}

