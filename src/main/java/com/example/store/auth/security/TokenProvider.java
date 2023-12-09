package com.example.store.auth.security;

import com.example.store.auth.service.AuthService;
import com.example.store.auth.type.MemberType;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.example.store.global.type.ErrorCode.*;

@Slf4j
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
     *
     * @param userEmail  회원 이메일
     * @param memberType 회원 구분
     * @return jwt 생성
     */
    public String createToken(String userEmail, MemberType memberType) {
        SecretKey key = getSecretKey();

        Claims claims = Jwts.claims().setSubject(userEmail).setId(this.generateRandomToken());
        claims.put("roles", memberType);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)   //토큰 생성 시간
                .setExpiration(new Date(now.getTime() + this.tokenValidTime)) //토큰 만료 시간
                .signWith(SignatureAlgorithm.HS256, key)    //사용할 암호화 알고리즘, 비밀키
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails
                = this.authService.loadUserByUsername(this.getUsername(token));

        return new UsernamePasswordAuthenticationToken(
                userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = this.parseClaims(token);
            return !claims.getExpiration().before(new Date());

        } catch (ExpiredJwtException e) {
            throw new JwtException(TOKEN_TIME_OUT.getDescription());
        } catch (UnsupportedJwtException e) {
            throw new JwtException(UNSUPPORTED_TOKEN.getDescription());
        } catch (IllegalArgumentException e) {
            throw new JwtException(WRONG_TOKEN.getDescription());
        } catch (MalformedJwtException e) {
            throw new JwtException(JWT_TOKEN_WRONG_TYPE.getDescription());
        } catch (JwtException e) {
            throw new JwtException(e.getMessage());
        }
    }

    private Claims parseClaims(String token) {
        SecretKey key = getSecretKey();

        return Jwts.parser().setSigningKey(key)
                .parseClaimsJws(token).getBody();
    }

    private SecretKey getSecretKey() {
        return new SecretKeySpec(Base64.getDecoder()
                .decode(this.secretKey), "HmacSHA256");
    }

    /**
     * 토큰 암호화
     * @return 인코딩 키
     */
    private String generateRandomToken(){
        UUID uuid = UUID.randomUUID();
        String token = uuid.toString();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(token.getBytes());

            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
