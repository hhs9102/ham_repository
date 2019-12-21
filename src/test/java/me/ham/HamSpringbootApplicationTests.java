package me.ham;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("{me.ham.circuitbreaker.resilience.controller.connector}")
public class HamSpringbootApplicationTests {

    @Test
    public void contextLoads() {
    }

}
