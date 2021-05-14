package com.training.sportsbetting.service.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethodReturnValueLoggerAspect {

    private final static Logger LOG = LoggerFactory.getLogger(MethodReturnValueLoggerAspect.class);

    @Pointcut("within(com.training.sportsbetting.service..*)")
    public void allServiceMethod() {
    };

    @AfterReturning(value = "allServiceMethod()", returning = "returnObject")
    public void returnValue(JoinPoint joinPoint, Object returnObject) throws Throwable {
        if (returnObject != null) {
            LOG.debug(methodDescription(joinPoint) + " Return value: " + returnObject.toString());
        } else {
            LOG.debug(methodDescription(joinPoint) + " No return value ");
        }
    }

    private String methodDescription(JoinPoint joinPoint) {
        String targetClass = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        return "After: Class: " + targetClass + " Method: " + methodName;
    }

}
