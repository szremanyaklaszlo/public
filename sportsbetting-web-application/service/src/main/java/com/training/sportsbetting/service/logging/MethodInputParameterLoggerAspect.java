package com.training.sportsbetting.service.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodInputParameterLoggerAspect {

    private final static Logger LOG = LoggerFactory.getLogger(MethodInputParameterLoggerAspect.class);

    @Pointcut("within(com.training.sportsbetting.service..*)")
    public void allServiceMethod() {
    };

    @Before("allServiceMethod()")
    public void methodParams(JoinPoint joinPoint) throws Throwable {
        Object[] methodParams = joinPoint.getArgs();
        if (methodParams.length == 0) {
            LOG.debug(methodDescription(joinPoint) + " No input parameter ");
        } else {
            LOG.debug(methodDescription(joinPoint) + " Input parameters: " + Arrays.toString(methodParams));
        }
    }

    private String methodDescription(JoinPoint joinPoint) {
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return "After: Class: " + targetClass + " Method: " + methodName;
    }
}
