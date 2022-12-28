package com.example.L11aopdemo.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect // @Aspect tells that this class contains all the advice
@Component
public class ExecutionTimeAspect {


    private static Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

//    @Around("execution(* com.example.L11aopdemo.TestService.*(..)) && @annotation(com.example.L11aopdemo.aop.LogExecutionTime)")
@Around("@annotation(com.example.L11aopdemo.aop.LogExecutionTime)")  // framework will scan all the methods with LogExecutionTime annotation and put a proxy method on that
    public Object log(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis(); // that proxy method put this code before execution of the method with annotation
        Object result = point.proceed(); // point is representing the method with the annotation and that proceeds its execution now
        // that proxy method put below lines of code after execution of the method with annotation
        long executionTime = System.currentTimeMillis() - start;
        logger.error("className={}, methodName={}, arguments={},  timeMs={}", new Object[]{((MethodSignature)MethodSignature.class.cast(point.getSignature())).getDeclaringTypeName(), ((MethodSignature)MethodSignature.class.cast(point.getSignature())).getMethod().getName(), point.getArgs(), executionTime});
        return result;
    }
// The above method records the execution time of a method

    @Before("execution(* com.example.L11aopdemo.TestController.*(..))")
    public void beforeMethod(){
        logger.error("Executing before method advice");
    }

    @After("execution(* com.example.L11aopdemo.TestService.*(..))")
    public void afterMethod(){
        logger.error("Executing after method advice");
    }


}


