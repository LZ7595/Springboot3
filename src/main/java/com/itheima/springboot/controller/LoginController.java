package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.pojo.User;
import com.itheima.springboot.service.UserService;
import com.itheima.springboot.utils.JwtUtil;
import com.itheima.springboot.exception.UserNotFoundException;
import com.itheima.springboot.exception.InvalidCredentialsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        try {
            User loginUser = userService.login(user);
            if (loginUser != null) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("username", loginUser.getUsername());
                String token = JwtUtil.generateJwt(map);
                HashMap<String, Object> responseData = new HashMap<>();
                responseData.put("token", token);
                responseData.put("user", loginUser.getUsername());
                return Result.success(responseData);
                // 登录成功，返回 token 和用户信息
            } else {
                // 在正常逻辑下，这里不应该被执行，因为 userService.login 应该抛出异常
                // 但如果出于某种原因 userService.login 返回了 null，这里将捕获这种情况
                log.warn("登录失败：未预期的用户返回null");
                return Result.error("登录失败，用户信息未找到");
            }
        } catch (UserNotFoundException e) {
            log.warn("登录失败：用户名不存在");
            return Result.error(e.getMessage());
        } catch (InvalidCredentialsException e) {
            log.warn("登录失败：密码错误");
            return Result.error(e.getMessage());
        } catch (Exception e) {
            log.error("登录过程中发生未知错误", e);
            return Result.error("登录失败，请稍后再试");
        }
    }
}