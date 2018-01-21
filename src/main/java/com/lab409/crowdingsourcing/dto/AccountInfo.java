package com.lab409.crowdingsourcing.dto;

import org.springframework.stereotype.Component;

@Component
public class AccountInfo {
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "username : " + username + ", password : " + password;
    }
}
