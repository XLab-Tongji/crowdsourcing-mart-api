package com.lab409.crowdingsourcing.dto;

public class DeveloperDto {
    private String username;
    private String email = "";
    private String mobile = "";

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public DeveloperDto(String username, String email, String mobile){
        this.email = email;
        this.mobile = mobile;
        this.username = username;
    }
}
