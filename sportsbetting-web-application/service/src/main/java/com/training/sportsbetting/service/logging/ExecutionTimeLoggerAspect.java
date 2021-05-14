package com.training.sportsbetting.service.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeLoggerAspect {

    private final static Logger LOG = LoggerFactory.getLogger(ExecutionTimeLoggerAspect.class);
    private long startTime;
    private long endTime;

    @Pointcut("within(com.training.sportsbetting.service..*)")
    public void allServiceMethod() {
    };

    @Before("allServiceMethod()")
    public void startTime(JoinPoint joinPoint) throws Throwable {
        startTime = System.currentTimeMillis();
    }

    @After(value = "allServiceMethod()")
    public void endTime(JoinPoint joinPoint) throws Throwable {
        endTime = System.currentTimeMillis();
        logTime(joinPoint);
    }

    private void logTime(JoinPoint joinPoint) {
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        LOG.info("After: Class: " + targetClass + " Method: " + methodName + " Execution time: " + (endTime - startTime) + "ms");
    }

}
