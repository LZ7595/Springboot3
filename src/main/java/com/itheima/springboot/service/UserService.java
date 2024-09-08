package com.itheima.springboot.service;

import com.itheima.springboot.pojo.User;

public interface UserService {
    User login(User user);

    User selectUserByName(String username);
    // 根据用户名和密码查找用户
    User findUserByUsernameAndPassword(String username, String password);

    // 更新用户的用户名
    void updateUsername(int userId, String newUsername);

    User findUserByUsername(String username);

    void updatePassword(int userId, String newPassword);
}