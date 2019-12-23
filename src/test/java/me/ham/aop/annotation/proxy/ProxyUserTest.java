package me.ham.aop.annotation.proxy;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("annotation 을 이용한 bean등록")
public class ProxyUserTest {

    @Autowired
    ProxyUserInterface proxyUserByAnnotation;

    @Test
    @DisplayName("프록시가 잘 생성되는지 확인")
    public void proxyUser(){
          assertEquals("USERNAME", proxyUserByAnnotation.getUsername());
    }

}