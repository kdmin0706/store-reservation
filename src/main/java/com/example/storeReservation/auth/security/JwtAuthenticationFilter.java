package com.example.storeReservation.auth.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Value("${spring.jwt.prefix}")
    private String tokenPrefix;

    @Value("${spring.jwt.header}")
    private String tokenHeader;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = this.resolveTokenFromRequest(request);

        //토큰 유효성 검증
        if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
            Authentication auth = this.tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

           log.info(String.format("[%s] -> %s", this.tokenProvider.getUsername(token), request.getRequestURI()));
        } else {
            log.info("토큰 유효성 검증 실패!!!");
        }

        filterChain.doFilter(request, response);
    }

    private String resolveTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (!ObjectUtils.isEmpty(token) && token.startsWith(tokenPrefix)) {
            return token.substring(tokenPrefix.length());
        }
        return null;
    }
}
