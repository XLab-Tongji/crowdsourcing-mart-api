package com.crazy.util;


import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/9.
 */
@Service
public class DateUtil {
    public SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*
    获取系统当前时间
     */
    public String getNowTime() {

        return df.format(new Date());
    }

    /*
    设置过期时间,当前时间加分钟数
     */
    public String setExpire(int minute) {

        Long total = System.currentTimeMillis();
        Long result = total + minute * 60 * 1000;
        return df.format(new Date(result));

    }

    /*
    字符转日期格式至timestamp
     */
    public Date Str2Date(String date) {
        Timestamp result = null;
        try {
            result = Timestamp.valueOf(date);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return result;
    }

    /*
    比较时间大小
     */
    public boolean check(Date date,int minute) {
        boolean status = false;
        Long setTime = date.getTime();
        Long nowTime = System.currentTimeMillis();
        if (nowTime < setTime) {

            status = true;
        }
        return status;
    }
}
