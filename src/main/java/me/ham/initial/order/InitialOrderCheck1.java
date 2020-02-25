package me.ham.initial.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class InitialOrderCheck1 implements ApplicationContextAware {
    {
        log.info("static :::::::::::::::::::InitialOrderCheck1 ");
    }

    public void onlyForTest(){
        log.info("InitialOrderCheck1 method is called");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("ApplicationContextAware is call in InitialOrderCheck1");
    }

    @PostConstruct
    public void postConstruct(){
        log.info("initialOrderCheck1 bean :::postConstruct");
    }
}
