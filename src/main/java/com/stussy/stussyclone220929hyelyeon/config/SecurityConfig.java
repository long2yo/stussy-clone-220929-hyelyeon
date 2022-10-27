package com.stussy.stussyclone220929hyelyeon.config;

import com.stussy.stussyclone220929hyelyeon.service.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//maven - spring boot security - version상관없이 복사해서 pom.xml에 붙여넣기
@EnableWebSecurity      //기존의 WebSecurityConfigurerAdapter 클래스를 해당 SecurityConfig로 대체하겠다
@Configuration
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailsService principalDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      super.configure(http); -> 로그인을 해야 들어가진다

        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()    //모든 요청시에 실행을 해라
                .antMatchers("/test/**", "/index")  //해당 요청 주소들은
                .authenticated()    //인증이 필요하다
                .anyRequest()   //antMatchers 외에 다른 모든 요청들은
                .permitAll()    //모든 접근 권한을 허용해라
                .and()  //그리고 , 이렇게 하던가 http.formLogin 이렇게도 쓸 수 있다
                .formLogin()    //formLogin방식으로 인증을 해라
                .usernameParameter("email")     //이메일로 바꿔야 이메일로 받는다 아니면 username으로 받는다
                .loginPage("/account/login")    //우리가 만든 로그인 페이지를 사용해라
                .loginProcessingUrl("/account/login")   //로그인 로직(PrincipalDetailsService) POST 요청
                .defaultSuccessUrl("/index");
    }

}
