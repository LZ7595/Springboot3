package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username} and password = #{password}")
    User selectUser(User user);
}
