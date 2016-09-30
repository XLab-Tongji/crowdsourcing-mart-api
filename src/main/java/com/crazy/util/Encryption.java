package com.crazy.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by SHIKUN on 2016/9/30.
 */
@Service
public class Encryption {
    public String doEncryption(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hasedpassword = passwordEncoder.encode(password);
        return hasedpassword;
    }

}
