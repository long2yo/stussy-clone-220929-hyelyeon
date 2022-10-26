package com.stussy.stussyclone220929hyelyeon.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAop {
    //annotation 연결
    @Pointcut("@annotation(com.stussy.stussyclone220929hyelyeon.aop.annotation.LogAspect)")
    private void annotationPointCut() {

    }

    @Pointcut("@execution(* com.stussy.stussyclone220929hyelyeon.controller.api..*.*(..))")
    private void executionPointCut() {}

    @Around("annotationPointCut()")
    private Object arround (ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();


        //여기서 부터는 log찍는 것
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        //다운캐스팅을 하면 변수명을 가지고 올 수 있다
        String[] argsNames = codeSignature.getParameterNames();

        StringBuilder argNameString = new StringBuilder();
        StringBuilder argDataString = new StringBuilder();

        for(int i = 0; i < args.length; i++){
            argNameString.append(argsNames[i]);
            argDataString.append(args[i].toString());

            if(i < args.length - 1){
                argNameString.append(",");
                argDataString.append(",");
            }

        }
        log.info("Method Call -- {}.{}({}) >> {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                argNameString.toString(),
                argDataString.toString());

        Object result = joinPoint.proceed();

        log.info("Method Return -- {}.{}({}) >> {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                argNameString.toString(),
                result);

        return result;
    }
}
