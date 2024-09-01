package com.varchar6.petcast.security;

import com.varchar6.petcast.domain.member.command.application.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public WebSecurity(MemberService memberService,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

         // csrf 비활성화
        http.csrf((csrf) -> csrf.disable());

        // AuthenticationManager 등록을 위한 builder 생성
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // memberService, bCryptPasswordEncoder 등록
        authenticationManagerBuilder
                .userDetailsService(memberService)
                .passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.authorizeHttpRequests((authz) ->
                authz.requestMatchers(
                        new AntPathRequestMatcher("/api/**")).permitAll()
                        .anyRequest().authenticated()
        )
                .authenticationManager(authenticationManager)       // authenticationManager 등록
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));    // 세션 비활성화

        return http.build();
    }
}
