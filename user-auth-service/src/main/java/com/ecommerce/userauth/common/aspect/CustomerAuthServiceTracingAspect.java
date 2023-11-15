package com.ecommerce.userauth.common.aspect;

import com.ecommerce.userauth.common.logger.CommonTraceLoggerAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomerAuthServiceTracingAspect extends CommonTraceLoggerAspect {

    @Pointcut("execution(* com.ecommerce.userauth.api..*(..)))")
    public void authControllerAspect() {}

    @Pointcut("execution(* com.ecommerce.userauth.service..*.*(..)))")
    public void authServiceTrace(){}

    @Around("authServiceTrace() && !noLogging()")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return trace(joinPoint);
    }

    @Around("authControllerAspect()")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        return trace(joinPoint);
    }
}
