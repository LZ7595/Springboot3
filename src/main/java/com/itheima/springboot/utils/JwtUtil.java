package com.itheima.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    // 使用与JwtTokenUtil相同的密钥
    private static final String SIGN_KEY = "lbxxJwtSecretKey1234567890lbxxJwtSecretKey1234567890"; // 修改为与JwtTokenUtil相同的密钥
    private static final long EXPIRE = 43200000L; //; // 12小时，单位毫秒60000L

    public static class JwtTokenExpiredException extends RuntimeException {
        public JwtTokenExpiredException(String message) {
            super(message);
        }
    }
    /**
     * 生成JWT令牌
     *
     * @param claims JWT负载中存储的内容
     * @return 生成的JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims) // 使用setClaims代替addClaims，因为addClaims通常用于添加自定义声明
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY) // 明确指定签名算法和密钥
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) // 设置过期时间
                .compact();
    }

    public static void validateJwtToken(String jwt) throws JwtTokenExpiredException {
        try {
            Jwts.parser().setSigningKey(SIGN_KEY).parseClaimsJws(jwt);
            // 如果上面的代码没有抛出异常，那么令牌是有效的
        } catch (ExpiredJwtException e) {
            // 如果捕获到ExpiredJwtException，则令牌已过期
            throw new JwtTokenExpiredException("JWT Token has expired");
        } catch (Exception e) {
            // 这里可以处理其他JWT相关的异常，但通常我们只关心过期异常
            // 注意：在生产环境中，你可能需要更细致地处理这些异常
            throw new RuntimeException("Invalid JWT Token", e);
        }
    }
    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT负载中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY) // 指定签名密钥
                .parseClaimsJws(jwt) // 解析JWT
                .getBody();
    }
}