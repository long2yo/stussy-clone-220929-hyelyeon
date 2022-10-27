package com.stussy.stussyclone220929hyelyeon.dto.account;

import com.stussy.stussyclone220929hyelyeon.domain.User;
import com.stussy.stussyclone220929hyelyeon.dto.validation.ValidationGroups;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterReqDto {
        // 검증 해줘야 함
        // 정규(표현)식 : @Pattern + (속성) 을 잡아줘야 한다


        //우리나라 이름은 최대 5글자이다.
        // {} : 횟수 또는 범위
        @NotBlank(message = "이름은 비워 둘 수 없습니다",
                groups = ValidationGroups.NotBlankGroup.class)
        @Size(min = 1, max = 3, message = "이름은 한글자에서 세글자 사이여야 합니다",
                groups = ValidationGroups.SizeCheckGroup.class)
        @Pattern(regexp = "^[가-힇]*$",
                message = "이름은 한글만 입력가능합니다",
                groups = ValidationGroups.PatternCheckGroup.class
        )
        private String lastName;



        @NotBlank(message = "성은 비워 둘 수 없습니다",
                groups = ValidationGroups.NotBlankGroup.class)//@NotBlank 있는곳에 다 적용시켜야 한다
        @Size(min = 1, max = 2, message = "성은 한글자에서 두글자 사이여야 합니다",
                groups = ValidationGroups.SizeCheckGroup.class) //@Size 있는곳에 다 적용시켜야 한다
        @Pattern(regexp = "^[가-힇]*$",
                message = "성은 한글만 입력가능합니다",
                groups = ValidationGroups.PatternCheckGroup.class    //@Pattern 있는곳에 다 적용시켜야 한다
        )
        private String firstName;


        @Email // Email은 정규식을 쓰지 않아서 꼭 NotBlank를 써줘야 한다
        @NotBlank(message = "이메일은 비워 둘 수 없습니다.",
                groups = ValidationGroups.NotBlankGroup.class
        )
        private String email;
        // 메세지를 안달면 기본적인 메세지가 자동으로 뜬다.
        // Email은 정규식으로 쓰지 않아서 꼭 NotBlank를 써줘야 한다.


        @NotBlank(message = "비밀번호는 비워 둘 수 없습니다",
                groups = ValidationGroups.NotBlankGroup.class)
        @Size(min = 8, max = 16, message = "비밀번호는 8자에서 16자 사이여야합니다.",
                groups = ValidationGroups.SizeCheckGroup.class)
        @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[~!@#$%^&*_])[a-zA-Z\\d-~!@#$%^&*_]*$",
                message = "비밀번호는 숫자, 영문, 특수기호를 하나 이상 포함하여 작성해야합니다",
                groups = ValidationGroups.PatternCheckGroup.class
        )
        private String password;
        // 임의의 한문자가 0번 또는 1번 발행하면은(?=.)
        // [소문자 대문자 중]의 0번 또는 1번 발생하면은 (*[a-zA-Z]) -> [true]가 됨.
        // 비밀번호의 경우 (?=.*)를 시작으로 한다.

        // \ : 확장문자(추가문자)의 시작
        // \d : 0 - 9(숫자)
        // 확장된 문자가 숫자면은(\\d)

        // [] : 이 중의 하나가 true 값 이어야 함

        // 다 정한 다음에 그 뒤에는 전체범위가 들어가야 한다.

        public User toUserEntity() {
                return User.builder()
                        .username(email)
                        .password(new BCryptPasswordEncoder().encode(password))
                        .name(firstName + lastName)
                        .email(email)
                        .role_id(1)
                        .build();
        }

}
