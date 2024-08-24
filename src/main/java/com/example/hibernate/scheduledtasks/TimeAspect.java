package com.example.hibernate.scheduledtasks;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TimeAspect {

    TimeDao timeDao;

    @Around("@annotation(com.example.hibernate.configTimed.Timed)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();

        Time time = Time.builder()
                .totalTime(end - start)
                .className(joinPoint.getSignature().getDeclaringTypeName())
                .methodName(joinPoint.getSignature().getName())
                .build();
        timeDao.save(time);
        return proceed;
    }
}
