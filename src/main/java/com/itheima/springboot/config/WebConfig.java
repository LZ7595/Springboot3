package com.itheima.springboot.config;

import com.itheima.springboot.utils.JwtAuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private JwtAuthenticationInterceptor jwtAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtAuthenticationInterceptor)
                .addPathPatterns("/user/**","/rcdhHt/**","/jcgyHt/**") // 指定需要拦截的路径模式，例如所有路径
                .excludePathPatterns("/login", "/register", "/error"); // 排除不需要拦截的路径
    }
}
