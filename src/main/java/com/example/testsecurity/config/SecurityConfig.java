package com.example.testsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth)->auth.
                requestMatchers("/","/login","loginProc","/join","/joinProc").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
        );

        http
                .formLogin((auth)-> auth.loginPage("/login")
                .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                .permitAll()
        );



//        http
//                .csrf((auth)->auth.disable());


        http
                .sessionManagement((auth)->auth.maximumSessions(1)//하나의 아이디에 대한 다중 로그인 허용 개수
                        .maxSessionsPreventsLogin(true));// 다중 로그인 개수를 초과하였을 경우 처리 방법 true: 새로운 로그인 차단 false: 기존 세션 하나 삭제


        //https://substantial-park-a17.notion.site/10-36136f5a91f647b499dbcb5a884aff72
        http
                .sessionManagement((session)->session.sessionFixation().changeSessionId());


        return http.build();
    }


}
