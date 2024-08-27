package com.itheima.springboot.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            handleError(response, "Authorization token not found");
            return false;
        }

        token = token.substring(7);

        try {
            jwtUtil.validateJwtToken(token);
            return true;
        } catch (JwtUtil.JwtTokenExpiredException e) {
            handleError(response, "Token has expired");
        } catch (Exception e) {
            handleError(response, "Invalid JWT Token");
        }

        return false;
    }

    private void handleError(HttpServletResponse response, String errorMessage) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 这里可以根据需要设置响应内容类型和内容
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
    }
}