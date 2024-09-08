package com.itheima.springboot.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    private int id;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password, int id) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // getter和setter
    public String getUsername() {
        return username;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

