package com.stussy.stussyclone220929hyelyeon.controller.api;

import com.stussy.stussyclone220929hyelyeon.dto.CMRespDto;
import com.stussy.stussyclone220929hyelyeon.dto.account.RegisterReqDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/api/account")
@RestController
public class AccountApi {


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReqDto registerReqDto, BindingResult bindingResult) {
// @RequestBody : json으로 날릴려고
// @Valid : 유효성 체크를 해준다
// @Valid를 주고 Dto에 사용하면 BindingResult 객체가 따라온다

        if(bindingResult.hasErrors()){
            log.error("유효성 검사 오류가 발생");

            Map<String, String> errorMap = new HashMap<String, String>();

            List<List<FieldError>> codeList = new ArrayList<List<FieldError>>();
            codeList.add(new ArrayList<FieldError>());  //Pattern
            codeList.add(new ArrayList<FieldError>());  //NotBlank

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());

                if(error.getCode().equals("Pattern")) {
                    codeList.get(0).add(error);
                }else if(error.getCode().equals("NotBlank")) {
                    codeList.get(1).add(error);
                }
            });
            log.info("codeList: {}", codeList);

            codeList.forEach(errorMapList -> {
                errorMapList.forEach(error ->{
                    errorMap.put(error.getField(), error.getDefaultMessage());
                });
                log.info("error: {}", errorMap);
            });


//            bindingResult.getFieldErrors().forEach(error -> {
//                log.info("Error: 코드({}), 필드명({}) , 메세지({})", error.getCode(), error.getField(), error.getDefaultMessage());
//                if(!error.getCode().equals("NotBlank")){
//
//                    //error 메세지 날려주는 기능
//                    errorMap.put(error.getField(), error.getDefaultMessage());
//                }
//            });
//            bindingResult.getFieldErrors().forEach(error -> {
//                log.info("Error: 코드({}), 필드명({}) , 메세지({})", error.getCode(), error.getField(), error.getDefaultMessage());
//                if(error.getCode().equals("NotBlank")){
//
//                    //error 메세지 날려주는 기능
//                    errorMap.put(error.getField(), error.getDefaultMessage());
//                }
//            });
            return ResponseEntity.badRequest().body(new CMRespDto<>(-1 , "유효성 검사 실패", errorMap));
        }

      log.info("{}", registerReqDto);
        return ResponseEntity.ok(null);
    }

}
