package me.ham.circuitbreaker.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@RestController
@RequestMapping("/hystrix")
public class CircuitBreakerController {

    @GetMapping("/consumer")
    @HystrixCommand(fallbackMethod = "fallback" ,commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000") //요청 대기 시간
            ,@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5") //최소 요청 건수
//            ,@HystrixProperty(name = "- circuitBreaker.sleepWindowInMilliseconds", value = "5000") //서킷브레이커 지속시간
    })
    public String Consumer(@RequestParam String path){
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
}
