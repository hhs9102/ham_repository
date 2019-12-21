package me.ham.aop;

import me.ham.user.User;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

public class AopTest {
    AtomicInteger testCallCnt = new AtomicInteger(0);
    static AtomicInteger adviceCallCnt = new AtomicInteger(0);

    private static ProxyFactoryBean proxyFactoryBean;

    @Before
    public void settings(){
        proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new User());

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("get*");

        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UpperAdvice()));
    }

    @Test
    public void pointCutTest(){
        //given
        User user = (User) proxyFactoryBean.getObject();
        user.setUsername("함호식");
        user.setPassword("password");

        //when
        user.getUsername();
        testCallCnt.addAndGet(1);

        //then
        assertEquals(testCallCnt.get(), adviceCallCnt.get());
    }

    class UpperAdvice implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            adviceCallCnt.addAndGet(1);
            return invocation.proceed();
        }
    }
}
