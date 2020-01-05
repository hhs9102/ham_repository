package me.ham.properties;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@TestPropertySource(value = "classpath:sample.properties")
@RunWith(SpringJUnit4ClassRunner.class)
public class PropertiesTest {
    @Value("${my.name}")
    String name;

    @Value("${my.age}")
    String age;

    @Value("${my.address}")
    String address;

    @Test
    public void hamTest(){
        assertEquals("hosik", name);
        assertEquals("30", age);
        assertEquals("shinchon", address);
    }

}
