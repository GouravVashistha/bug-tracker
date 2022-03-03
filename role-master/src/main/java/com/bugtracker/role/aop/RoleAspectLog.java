package com.bugtracker.role.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Slf4j
public class RoleAspectLog {

    @Around("execution((public * com.bugtracker.role.controller.*.*(..))")
    public void logBeforeAllControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        log.info("Inside "+ proceedingJoinPoint.getSignature().getName()+ " of User Controller");
        List<String> responses= (List<String>) proceedingJoinPoint.proceed();
        log.info("Method "+ proceedingJoinPoint.getSignature().getName()+ " returned: {}", responses);
    }
}
