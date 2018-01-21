package com.lab409.crowdingsourcing.dto;

import com.lab409.crowdingsourcing.entity.UserInfoDetail;
import org.springframework.stereotype.Component;

@Component
public class AccountDetail {
    private java.lang.String tokenid;
    private String username;
    private String realname;
    private String mobile;
    private String email;

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
