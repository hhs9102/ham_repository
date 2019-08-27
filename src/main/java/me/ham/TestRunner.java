package me.ham;

import me.ham.config.properties.HamProperties;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    HamProperties properties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Assert.assertEquals("ham hosik", properties.getFullname());
    }
}
