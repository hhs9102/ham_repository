package me.ham.aop.proxy;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration("file:/Users/hamhosik/IdeaProjects/ham_springboot/src/main/resources/beans-context.xml")
@RunWith(SpringRunner.class)
public class ProxyUserTest{

    @Autowired
    ProxyUser proxyUser;

    @Test
    @DisplayName("프록시가 잘 생성되는지 확인")
    public void proxyUserTset(){
        assertEquals("HAMHOSIK", proxyUser.getUsername());
    }

}