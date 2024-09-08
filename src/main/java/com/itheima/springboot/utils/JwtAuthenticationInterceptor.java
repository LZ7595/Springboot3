package com.itheima.springboot.utils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null ||!token.startsWith("Bearer ")) {
            handleError(response, "Authorization token not found");
            return false;
        }

        token = token.substring(7);

        try {
            jwtUtil.validateJwtToken(token);
            Claims claims = jwtUtil.parseJWT(token);
            String username = claims.get("username", String.class);
            request.setAttribute("username", username);
            return true;
        } catch (JwtUtil.JwtTokenExpiredException e) {
            // Token 过期时的处理
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Token has expired. Please log in again.\"}");
            return false;
        } catch (Exception e) {
            handleError(response, "Invalid JWT Token");
            return false;
        }
    }


    private void handleError(HttpServletResponse response, String errorMessage) throws Exception {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + errorMessage + "\"}");
    }
}