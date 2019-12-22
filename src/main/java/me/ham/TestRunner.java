package me.ham;

import me.ham.config.properties.HamProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements ApplicationRunner {

    @Autowired
    HamProperties properties;

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        Assert.assertEquals("ham hosik", properties.getFullname());
//        RestTemplate restTemplate = restTemplateBuilder.build();
//
//        StopWatch stopWatch = new StopWatch();
//
//        stopWatch.start();
//        String helloResult1 = restTemplate.getForObject("http://localhost:8080/test/sleep/5000", String.class);
//        System.out.println(helloResult1);
//
//        String helloResult2 = restTemplate.getForObject("http://localhost:8080/test/sleep/5000", String.class);
//        System.out.println(helloResult2);
//
//        stopWatch.stop();
//        System.out.println(stopWatch.prettyPrint());

        String[] beans = applicationContext.getBeanDefinitionNames();
//            Arrays.sort(beans);
            for(String bean : beans){
//            if(bean.toUpperCase().indexOf("CIRCUITBREAKER") > -1){
                System.out.println(bean);
//            }
        }
    }




}
