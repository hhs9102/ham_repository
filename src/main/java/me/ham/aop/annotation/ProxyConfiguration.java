package me.ham.aop.annotation;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfiguration {

    @Bean
    //어드바이저 검색 및 등록을 위한 빈등록
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    //포인트컷
    public Pointcut upperPointcutByConfiguration(){
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("getUsername");
        pointcut.setClassFilter(clazz -> clazz.getName().indexOf("ProxyUser")>-1);
        return pointcut;
    }

    @Bean
    //어드바이스
    public MethodInterceptor upperAdviceByConfiguration(){
        return invocation -> {
            return invocation.proceed().toString().toUpperCase();
        };
    }

    @Bean
    //어드바이저 생성 = 포인트컷 + 어드바이스
    public DefaultPointcutAdvisor upperAdvisorByConfiguration(Pointcut upperPointcutByConfiguration, MethodInterceptor upperAdviceByConfiguration){
        return new DefaultPointcutAdvisor(upperPointcutByConfiguration, upperAdviceByConfiguration) ;
    }

//    @Bean
    //프록시 팩토리빈 등록
//    public ProxyFactoryBean proxyFactoryBean(ApplicationContext applicationContext){
//        Object proxyUserByAnnotation = applicationContext.getBean("proxyUserByAnnotation");
//        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
//        proxyFactoryBean.setTarget(proxyUserByAnnotation);
//        proxyFactoryBean.setInterceptorNames(new String[]{"upperAdvisorByConfiguration"});
//        return proxyFactoryBean;
//    }
}
