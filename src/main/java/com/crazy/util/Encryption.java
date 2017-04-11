package com.crazy.util;

import com.crazy.repository.AccountLoginRepository;
import com.crazy.mapper.AccountMapper;
import com.crazy.entity.AccountLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * Created by SHIKUN on 2016/9/30.
 */
@Service
public class Encryption {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private AccountLoginRepository accountLoginRepository;

    @Autowired
    private DateUtil dateUtil;


    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String doEncryption(String password){
        String encryptPassword = passwordEncoder.encode(password);
        return encryptPassword;
    }

    public boolean checkPassword(String password,String encryptPassword) {
        return passwordEncoder.matches(password,encryptPassword);
    }

    /*
    产生token,设置时间
     */
    public static String createToken() {

        return UUID.randomUUID().toString().replace("-", "");
    }

    /*
    判断token是否有效
     */
    public String tokenValidate(String token) {

        String result = null;
     //   AccountLogin tokenInfo = accountMapper.getTokenInfo(token);
        AccountLogin tokenInfo = accountLoginRepository.findByToken(token);
        Date expire_time = tokenInfo.getExpire_time();
        Long account_id = tokenInfo.getAccount_id();
        Long id = tokenInfo.getId();

        boolean checkstatus = dateUtil.check(expire_time, 30);

        if (checkstatus == true) {
            result = "token有效";
        } else {
            token = createToken();
            AccountLogin accountLogin = accountLoginRepository.findById(id);
            accountLogin.setToken(token);
            accountLogin.setCreate_time(dateUtil.Str2Date(dateUtil.getNowTime()));
            accountLogin.setExpire_time(dateUtil.Str2Date(dateUtil.setExpire(30)));
            /*
            accountMapper.updateToken(token, dateUtil.Str2Date(dateUtil.getNowTime()),
                    dateUtil.Str2Date(dateUtil.setExpire(30)), id);
                    */
            accountLoginRepository.save(accountLogin);
            result = token;
        }

        return result;
    }


}
