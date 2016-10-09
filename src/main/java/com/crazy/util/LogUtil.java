package com.crazy.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by SHIKUN on 2016/10/9.
 */
@Service
public class LogUtil {

    //产生token,设置时间
    public String createToken() {

        return UUID.randomUUID().toString().replace("-", "");
    }



}
