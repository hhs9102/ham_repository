package me.ham.async.pet.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;



@Aspect
@Component
@Slf4j
public class PetAspect {
    @Around("execution(* me.ham.async.pet.PetServiceImpl.*(..))")
    public Object stopWatchPetConnectTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("[{}]"+proceedingJoinPoint.getSignature().getName()+") executionTime : "+stopWatch.getTotalTimeMillis(), Thread.currentThread().getName());
        return result;
    }
}
