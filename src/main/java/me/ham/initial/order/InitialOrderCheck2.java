package me.ham.initial.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class InitialOrderCheck2 implements ApplicationContextAware {
    InitialOrderCheck1 initialOrderCheck1;
    {
        log.info("static::::::::::::");
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.initialOrderCheck1 = (InitialOrderCheck1) applicationContext.getBean("initialOrderCheck1");
        log.info("ApplicationContextAware is call & set initialOrderCheck1 Bean");
        initialOrderCheck1.onlyForTest();
    }

    @PostConstruct
    public void postConstruct(){
        log.info("initialOrderCheck2 bean :::postConstruct");
    }
}
