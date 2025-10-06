package com.thymeleaf.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.thymeleaf.model.Account;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SuppressWarnings("deprecation")
public class JwtUtil {

    // 有效时间
    private static final long limitTime = 1000 * 60 * 5;
    // 算法秘钥
    private static final String signature = "1234567890123456789012345678901234567890123";

    /**
     * 对用户信息进行jwt加密并返回token
     *
     * @param  account
     * @return
     */
    public static String createJwt(Account account) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // Heard 类型
                .setHeaderParam("typ", "JWT")
                // 加密算法
                .setHeaderParam("alg", "HS256")
                // payload
                .claim("name", account.getUsername()).claim("role", account.getApplication())
                // subject
                .setSubject("jwt-test")
                // 有效时间
                .setExpiration(new Date(System.currentTimeMillis() + limitTime))
                // ID
                .setId(UUID.randomUUID().toString())
                // Signature 签名
                .signWith(SignatureAlgorithm.HS256, signature)
                // 拼接三部分
                .compact();

        return jwtToken;
    }

    /**
     * 解密token并返回json信息的Map
     *
     * @param  token
     * @return
     */
    public static Map<String, String> parseToken(String token) {
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        Map<String, String> result = new HashMap<String, String>();
        result.put("name", claims.get("name").toString());
        result.put("role", claims.get("role").toString());
        return result;
    }
}
