package com.example.storeReservation.auth.security;

import com.example.storeReservation.auth.service.AuthService;
import com.example.storeReservation.auth.type.MemberType;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

import static com.example.storeReservation.auth.security.TokenUtil.generateRandomToken;
import static com.example.storeReservation.global.type.ErrorCode.JWT_TOKEN_WRONG_TYPE;
import static com.example.storeReservation.global.type.ErrorCode.TOKEN_TIME_OUT;

@Component
@RequiredArgsConstructor
public class TokenProvider {
    private final AuthService authService;

    @Value("${spring.jwt.token-valid-time}")
    private long tokenValidTime;

    @Value("${spring.jwt.secret}")
    private String secretKey;

    /**
     * 토큰 생성
     * @param userEmail 회원 이메일
     * @param memberType  회원 구분
     * @return jwt 생성
     */
    public String createToken(String userEmail, MemberType memberType) {
        SecretKey key = new SecretKeySpec(Base64.getDecoder()
                .decode(this.secretKey), "HmacSHA256");

        Claims claims = Jwts.claims().setSubject(userEmail).setId(generateRandomToken());
        claims.put("roles", memberType);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)   //토큰 생성 시간
                .setExpiration(new Date(now.getTime() + this.tokenValidTime)) //토큰 만료 시간
                .signWith(SignatureAlgorithm.HS256, key)    //사용할 암호화 알고리즘, 비밀키
                .compact();
    }

    public Authentication getAuthentication(String jwt) {
        UserDetails userDetails
                = this.authService.loadUserByUsername(this.getUsername(jwt));

        return new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities());
    }

    /**
     * token으로 username(사용자 ID) 찾기
     *
     * @param token: 토큰 내용
     * @return username
     */
    public String getUsername(String token) {
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }

        Claims claims = this.parseClaims(token);

        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token) {
        SecretKey key = new SecretKeySpec(Base64.getDecoder()
                .decode(this.secretKey), "HmacSHA256");

        try {
            return Jwts.parser().setSigningKey(key)
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException(TOKEN_TIME_OUT.getDescription());
        } catch (SignatureException e) {
            throw new JwtException(JWT_TOKEN_WRONG_TYPE.getDescription());
        }
    }
}
