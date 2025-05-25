package org.app.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
  @Before("execution(* org.app.api.controller.*.*(..))")
  public void logMethodName(JoinPoint joinPoint) {
    String methodName = joinPoint.getSignature().getName();
    log.info("Method called: {} ", methodName);
  }

  @Around("execution(* org.app.api.controller.*.*(..))")
  public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    Instant start = Instant.now();
    Object result = joinPoint.proceed();
    Instant end = Instant.now();
    long duration = end.toEpochMilli() - start.toEpochMilli();
    log.info("Method executed in: {} ms", duration);
    return result;
  }
}
