package com.itheima.springboot.utils;

import io.jsonwebtoken.Claims;
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
        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            // 移除"Bearer "前缀
            token = token.substring(7);

            try {
                // 验证token是否有效
                JwtUtil.validateJwtToken(token);
                // 如果token有效，则继续执行后续的处理器
                return true;
            } catch (JwtUtil.JwtTokenExpiredException e) {
                // 如果token过期，则返回401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token has expired");
                return false;
            } catch (Exception e) {
                // 处理其他JWT验证异常
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid JWT Token");
                return false;
            }
        }
        // 如果没有token或token格式不正确，则也可能需要返回401或其他错误码
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Authorization token not found");
        return false;
    }
}
