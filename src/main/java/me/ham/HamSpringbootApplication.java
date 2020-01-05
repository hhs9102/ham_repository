package me.ham;

import org.apache.catalina.LifecycleException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

@EnableAspectJAutoProxy
@SpringBootApplication
@ImportResource(locations = "classpath:beans-context.xml")
public class HamSpringbootApplication {
    public static void main(String[] args) throws LifecycleException {
        SpringApplication.run(HamSpringbootApplication.class, args);
}
}
