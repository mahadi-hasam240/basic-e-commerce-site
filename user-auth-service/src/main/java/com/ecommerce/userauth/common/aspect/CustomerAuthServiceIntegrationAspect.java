package com.ecommerce.userauth.common.aspect;

import com.ecommerce.userauth.common.logger.CommonIntegrationLoggerAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerAuthServiceIntegrationAspect extends CommonIntegrationLoggerAspect {

    @Pointcut("execution(public * com.ecommerce.userauth.rest.client..*.*(..))")
    public void authIntegrationTrace() {
    }

    @Around("authIntegrationTrace()")
    public Object traceUserIntegration(ProceedingJoinPoint joinPoint) throws Throwable {
        return trace(joinPoint);
    }

}
