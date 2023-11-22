package com.example.reservation.member.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

public class TokenUtil {

    /**
     * 토큰 암호화
     * @return 인코딩 키
     */
    public static String generateRandomToken(){
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
