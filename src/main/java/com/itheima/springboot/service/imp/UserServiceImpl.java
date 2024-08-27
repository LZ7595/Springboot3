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
    }}