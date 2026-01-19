package com.petclinic.petclinic.tech;

import com.petclinic.petclinic.core.FindAll;
import com.petclinic.petclinic.core.Visit;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = Logger.getLogger(LogAspect.class.getName());

    //@Before("execution(* com.petclinic..*Service.*(..))")
    //@Before("execution(* com.petclinic.petclinic.core.*Repository.*(..))")
    @Before("execution(* com.petclinic.petclinic.core.*Service.*(..))")
    //@Before("execution(* com.petclinic.petclinic.core.*Service.findAll())")
    public void log(JoinPoint joinPoint) throws Throwable {
        logger.info("Service exécuté : " + joinPoint.getSignature().getName() + " - " + joinPoint.getTarget().getClass().getName());
    }

    @Around("@annotation(findAll)")
    public Object aroundLog(ProceedingJoinPoint joinPoint, FindAll findAll) throws Throwable {
        logger.info("Début : " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        logger.info("Fin : " + joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName());
        return result;
    }
}
