package com.kemalkeskin.junittestdeneme.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

   private Logger logger=Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.kemalkeskin.junittestdeneme.service.*.*(..))")
    private void forServicePackage(){

    }

    @Pointcut("execution(* com.kemalkeskin.junittestdeneme.repository.*.*(..))")
    private void forRepositoryPackage(){

    }
    @Pointcut("execution(* com.kemalkeskin.junittestdeneme.controller.*.*(..))")
    private void forControllerPackage(){

    }
    @Pointcut("forServicePackage() || forRepositoryPackage() || forControllerPackage())")
    private void forAllPackage(){
    }

    @Before("forAllPackage()")
    public void before(JoinPoint joinPoint){
        String method=joinPoint.getSignature().toShortString();
        logger.info("@Before: calling method: "+method);

     Object[] args=joinPoint.getArgs();
     for (Object arg :args)
      System.out.println("argument: "+arg);
    }

    @AfterReturning(pointcut="forAllPackage()",returning="result")
    public void  afterReturning(JoinPoint joinPoint,Object result){
     String method=joinPoint.getSignature().toShortString();
     logger.info("@AfterReturning: calling from method: "+method);

     logger.info("result: "+result);

    }

}
