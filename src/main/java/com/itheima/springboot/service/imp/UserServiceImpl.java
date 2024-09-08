package com.itheima.springboot.service.imp;


import com.itheima.springboot.mapper.UserMapper;
import com.itheima.springboot.pojo.User;
import com.itheima.springboot.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User login(User user){
        User loginUser = userMapper.selectUser(user);
        return loginUser;
    }

    public User selectUserByName(String username){
        User user = userMapper.selectUserByName(username);
        return user;
    }
    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        return userMapper.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public void updateUsername(int userId, String newUsername) {
        userMapper.updateUsername(userId, newUsername);
    }

    @Override
    public void updatePassword(int userId, String newPassword) {
        userMapper.updatePassword(userId, newPassword);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.selectUserByName(username);
    }
}