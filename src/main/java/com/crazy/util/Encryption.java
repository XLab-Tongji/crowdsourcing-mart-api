package com.crazy.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by SHIKUN on 2016/9/30.
 */
@Service
public class Encryption {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String doEncryption(String password){
        String encryptPassword = passwordEncoder.encode(password);
        return encryptPassword;
    }

    public boolean checkPassword(String password,String encryptPassword) {
        return passwordEncoder.matches(password,encryptPassword);
    }

}
