package me.ham;

import org.apache.catalina.LifecycleException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HamSpringbootApplication {
    public static void main(String[] args) throws LifecycleException {
        SpringApplication.run(HamSpringbootApplication.class, args);
    }
}
