package com.itheima.springboot.controller;

import com.itheima.springboot.pojo.Result;
import com.itheima.springboot.pojo.User;
import com.itheima.springboot.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/userInfo")
    public Result selectUserByName() {
        String username = (String) request.getAttribute("username");
        if (username!= null) {
            return Result.success(userService.selectUserByName(username));
        } else {
            return Result.error("无法获取用户名");
        }
    }
    @PostMapping("/updateName")
    public Result updateUsername(@RequestBody Map<String, String> requestBody) {
        String oldname = requestBody.get("oldname");
        String newname = requestBody.get("newname");
        String password = requestBody.get("password");
        try {
            // 根据 oldname 和 password 查找用户
            User existingUser = userService.findUserByUsernameAndPassword(oldname, password);
            if (existingUser!= null) {
                userService.updateUsername(existingUser.getId(), newname);
                // 删除本地存储的用户信息（假设你有方法可以实现此操作）
                // deleteLocalStorageUser();
                return Result.success("修改成功");
            } else {
                return Result.error("修改失败：用户名或密码错误");
            }
        } catch (Exception e) {
            // 记录错误日志
            return Result.error("修改失败：服务器内部错误");
        }
    }
    @PostMapping("/updatePwd")
    public Result updatePassword(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String oldPwd = requestBody.get("oldPwd");
        String newPwd = requestBody.get("newPwd");
        try {
            // 根据用户名查找用户
            User existingUser = userService.findUserByUsername(username);
            if (existingUser!= null && existingUser.getPassword().equals(oldPwd)) {
                userService.updatePassword(existingUser.getId(), newPwd);
                return Result.success("修改成功");
            } else {
                return Result.error("修改失败：用户名或旧密码错误");
            }
        } catch (Exception e) {
            return Result.error("修改失败：服务器内部错误");
        }
    }
}
