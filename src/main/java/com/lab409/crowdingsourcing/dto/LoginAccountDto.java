package com.lab409.crowdingsourcing.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginAccountDto {
    private String username;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "username:" + this.username + " ,password:" + this.password + " ,email:" + this.email;
    }
}
