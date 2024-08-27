package com.itheima.springboot.Request;

import com.itheima.springboot.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);
    // 注意：实际开发中，不应根据明文密码查询用户，这里仅为示例
}
