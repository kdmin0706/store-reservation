package com.example.store.auth.config;

import com.example.store.auth.security.AuthenticationFilter;
import com.example.store.auth.security.JwtAccessDeniedHandler;
import com.example.store.auth.security.JwtAuthenticationEntryPoint;
import com.example.store.auth.security.JwtExceptionFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationFilter authenticationFilter;
    private final JwtExceptionFilter jwtExceptionFilter;

    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeHttpRequests()
                    .antMatchers("/api/**/", "/exception/**/").permitAll()
                    .antMatchers("/api/partner/**").hasRole("PARTNER")
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)   //인증 예외 처리
                    .accessDeniedHandler(jwtAccessDeniedHandler)                //인가(권한) 예외 처리
                .and()
                    .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(jwtExceptionFilter, AuthenticationFilter.class)
                    ;

        return httpSecurity.build();
    }
}
