package com.crazy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by SHIKUN on 2016/9/30.
 */
@Service
public class Encryption {

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String doEncryption(String password){
        String encryptPassword = passwordEncoder.encode(password);
        return encryptPassword;
    }

    public boolean checkPassword(String password,String encryptPassword) {
        return passwordEncoder.matches(password,encryptPassword);
    }

    //产生token,设置时间
    public String createToken() {

        return UUID.randomUUID().toString().replace("-", "");
    }
}
