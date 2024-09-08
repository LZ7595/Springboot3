package com.itheima.springboot.mapper;

import com.itheima.springboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username} and password = #{password}")
    User selectUser(User user);

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectUserByName(String name);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password}")
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    // 更新用户的用户名
    @Update("UPDATE user SET username = #{newUsername} WHERE id = #{userId}")
    void updateUsername(@Param("userId") int userId, @Param("newUsername") String newUsername);

    @Update("UPDATE user SET password = #{newPassword} WHERE id = #{userId}")
    void updatePassword(@Param("userId") int userId, @Param("newPassword") String newPassword);

}
