package com.stussy.stussyclone220929hyelyeon.controller.api;

import com.stussy.stussyclone220929hyelyeon.dto.account.RegisterReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/account")
@RestController
public class AccountApi {


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReqDto registerReqDto) {
//        RequestBody : json으로 날릴려고

      log.info("{}", registerReqDto);
        return ResponseEntity.ok(null);
    }

}
