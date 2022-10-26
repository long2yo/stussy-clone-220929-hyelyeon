package com.stussy.stussyclone220929hyelyeon.aop;

import com.stussy.stussyclone220929hyelyeon.dto.CMRespDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class ValidationAop {

    @Pointcut("@annotation(com.stussy.stussyclone220929hyelyeon.aop.annotation.ValidAspect)")
    private void pointCut() {

    }

    @Around("pointCut()")
    private Object arround(ProceedingJoinPoint joinPoint) throws  Throwable {
        Object[] args = joinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args){
            if(arg.getClass() == BeanPropertyBindingResult.class){
                bindingResult = (BeanPropertyBindingResult) arg;    //BeanPropertyBindingResult 객체 찾앗다!
                break;
            }
    }

        if(bindingResult == null){
            return joinPoint.proceed();
        }

//      AccountApi에 있는 것을 복사해서 수정
        if(bindingResult.hasErrors()){
            log.error("유효성 검사 오류가 발생");

            Map<String, String> errorMap = new HashMap<String, String>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            return ResponseEntity.badRequest().body(new CMRespDto<>(-1,"유효성 검사 실패",errorMap));
        }

//        return null; 해도 되고
        return joinPoint.proceed();

    }

}
