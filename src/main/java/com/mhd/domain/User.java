package com.mhd.domain;

/**
 * @author MouHongDa
 * @date 2023/11/25 14:25
 */
public class User {
    private Long id;

    private String username;

    private String userPassword;

    public User(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }
}
