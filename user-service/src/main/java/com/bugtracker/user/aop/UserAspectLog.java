package com.bugtracker.user.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class UserAspectLog {

    @Around("execution((public * com.bugtracker.user.controller.*.*(..))")
    public void logBeforeAllControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("Inside "+ proceedingJoinPoint.getSignature().getName()+ " of User Controller");
        List<String> responses= (List<String>) proceedingJoinPoint.proceed();
        log.info("Method "+ proceedingJoinPoint.getSignature().getName()+ " returned: {}", responses);
    }

}
