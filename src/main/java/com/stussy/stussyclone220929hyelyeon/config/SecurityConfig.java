package com.stussy.stussyclone220929hyelyeon.config;

import com.stussy.stussyclone220929hyelyeon.handler.auth.AuthFailureHandler;
import com.stussy.stussyclone220929hyelyeon.service.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//maven - spring boot security - version상관없이 복사해서 pom.xml에 붙여넣기
@EnableWebSecurity      //기존의 WebSecurityConfigurerAdapter 클래스를 해당 SecurityConfig로 대체하겠다
@Configuration
@RequiredArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    //암호화 한것을 복호화 시키는 것
    @Bean   //@Configuration에서 꼭 @Bean을 해줘야-> IOC에 등록 됨
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      super.configure(http); -> 로그인을 해야 들어가진다

        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()    //모든 요청시에 실행을 해라
//                .antMatchers("/test/**", "/index")  //해당 요청 주소들은
//                .authenticated()    //인증이 필요하다
                .antMatchers("/admin/**")   //이 페이지로 들어오면 권한 없다
                .access("hasRole('ADMIN') or hasRole('MANAGER')")//여러개의 권한 적용
                //                .hasRole("ADMIN") //하나의 권한 적용
                .antMatchers("/account")  //해당 요청 주소들은
                .access("hasRole('USER') or hasRole('ADMIN') or hasRole('MANAGER')")
//                .authenticated()    //인증이 필요하다

                .antMatchers("/", "/index", "/collections/**")
                .permitAll()
                .antMatchers("/account/login", "/account/register")
                .permitAll()

                //<<<<<<<<<<<<<<<<Resource>>>>>>>>>>>>>>>>>>>>>>>>//
                .antMatchers("/static/**", "/image/**")
                .permitAll()    //모든 접근을 허용해라

                //<<<<<<<<<<<<<<<<API>>>>>>>>>>>>>>>>>>>>>>>>//
                .antMatchers("/api/account/register")
                .permitAll()

                .anyRequest()   //antMatchers 외에 다른 모든 요청들은
                .permitAll()    //모든 접근 권한을 허용해라
                //                .denyAll()    //모든 접근을 차단해라 - 수업중이라서 주석처리

                .and()  //그리고 , 이렇게 하던가 http.formLogin 이렇게도 쓸 수 있다
                .formLogin()    //formLogin방식으로 인증을 해라
                .usernameParameter("email")     //이메일로 바꿔야 이메일로 받는다 아니면 username으로 받는다
                .loginPage("/account/login")    //우리가 만든 로그인 페이지를 사용해라
                .loginProcessingUrl("/account/login")   //로그인 로직(PrincipalDetailsService) POST 요청
                .failureHandler(new AuthFailureHandler())   //오류가 뜨면 띄워라
                .defaultSuccessUrl("/index");
    }
}

