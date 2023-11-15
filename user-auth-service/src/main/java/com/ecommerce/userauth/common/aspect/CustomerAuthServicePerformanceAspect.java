package com.ecommerce.userauth.common.aspect;

import com.ecommerce.userauth.common.logger.CommonPerformanceLoggerAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerAuthServicePerformanceAspect extends CommonPerformanceLoggerAspect {

    @Pointcut("execution(public * com.ecommerce.userauth.repository..*.*(..))")
    public void authRepositoryPerformanceTrace() {}

    @Around("authRepositoryPerformanceTrace()")
    public Object calculateAuthServicePerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        return tracePerformance(joinPoint);
    }

}
