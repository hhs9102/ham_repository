package me.ham.user;

import lombok.Data;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest {

    @Data
    class TestObject{
        private String name;
    }

    @Test
    public void testOptional(){
        TestObject testObject = null;
        String name = Optional.ofNullable(testObject).map(c->c.getName()).orElse("2");
        if(Optional.ofNullable(testObject).isPresent()){
            System.out.println("T");
        }
        System.out.println(name);
    }
}
