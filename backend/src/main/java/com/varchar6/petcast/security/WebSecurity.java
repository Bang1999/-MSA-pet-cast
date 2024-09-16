package com.varchar6.petcast.security;

import com.varchar6.petcast.domain.member.query.service.MemberAuthenticationService;
import com.varchar6.petcast.utility.JwtUtil;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final MemberAuthenticationService memberAuthenticationService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment environment;
    private final JwtUtil jwtUtil;

    @Autowired
    public WebSecurity(MemberAuthenticationService memberAuthenticationService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       Environment environment,
                       JwtUtil jwtUtil) {
        this.memberAuthenticationService = memberAuthenticationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

         // csrf 비활성화
        http.csrf(AbstractHttpConfigurer::disable);

        // AuthenticationManager 등록을 위한 builder 생성
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        // memberService, bCryptPasswordEncoder 등록
        authenticationManagerBuilder
                .userDetailsService(memberAuthenticationService)
                .passwordEncoder(bCryptPasswordEncoder);

        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        http.authorizeHttpRequests(authz ->
                authz
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()     // for dev
//                        .requestMatchers(new AntPathRequestMatcher("/login")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/v1/members/sign-up")).permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/api/v1/notice", "POST")).hasRole(Role.ADMIN.getType())
//                        .requestMatchers(new AntPathRequestMatcher("/api/v1/**")).hasRole(Role.CUSTOMER.getType())
                        .anyRequest().authenticated()
        )
                .authenticationManager(authenticationManager)       // authenticationManager 등록
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));    // 세션 비활성화
        http.addFilter(getAuthenticationFilter(authenticationManager));
        http.addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private Filter getAuthenticationFilter(AuthenticationManager authenticationManager) {
        return new AuthenticationFilter(authenticationManager, environment);
    }
}
