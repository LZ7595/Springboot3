package com.itheima.springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private String secretKey = "lbxxJwtSecretKey1234567890lbxxJwtSecretKey1234567890";

    // 解析JWT令牌
    public Claims parseJwt(String jwt) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    // 检查JWT令牌是否过期
    public boolean isTokenExpired(String jwt) {
        try {
            final Date expiration = parseJwt(jwt).getExpiration();
            return expiration.before(new Date());
        } catch (MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            // 令牌格式错误或不受支持，也认为是过期（或无效）
            return true;
        }
    }
}