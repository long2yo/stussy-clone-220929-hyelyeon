package com.stussy.stussyclone220929hyelyeon.dto.account;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class RegisterReqDto {

        // 검증 해줘야 함
        // 정규(표현)식 : @Pattern + (속성) 을 잡아줘야 한다

        @Pattern(regexp = "^[가-힇]{1,3}$", message = "오류메세지 : 이름은 한글만 입력가능하며 한글자 이상 세글자 이하로 작성하세요.")
        private String lastName;
        //우리나라 이름은 최대 5글자이다.
        // {} : 횟수 또는 범위

        @Pattern(regexp = "^[가-힇]{1,2}$", message = "성은 한글만 입력가능하며 한글자 이상 두글자 이하로 작성하세요.")
        private String firstName;

        @Email
        @NotBlank(message = "이메일은 비워 둘 수 없습니다.")
        private String email;
        // 메세지를 안달면 기본적인 메세지가 자동으로 뜬다.
        // Email은 정규식으로 쓰지 않아서 꼭 NotBlank를 써줘야 한다.

        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-~!@#$%^&*_]{8,16}$", message = "비밀번호는 숫자, 영문(대소문자), 특수기호를 하나 이상 포함하여야하며 8자 이상 16자 이하로 작성해야합니다.")
        @NotBlank(message = "비밀번호는 비워 둘 수 없습니다.")
        private String password;
        // 임의의 한문자가 0번 또는 1번 발행하면은(?=.)
        // [소문자 대문자 중]의 0번 또는 1번 발생하면은 (*[a-zA-Z]) -> [true]가 됨.
        // 비밀번호의 경우 (?=.*)를 시작으로 한다.

        // \ : 확장문자(추가문자)의 시작
        // \d : 0 - 9(숫자)
        // 확장된 문자가 숫자면은(\\d)

        // [] : 이 중의 하나가 true 값 이어야 함

        // 다 정한 다음에 그 뒤에는 전체범위가 들어가야 한다.



}
