package com.crazy.service;

import com.crazy.entity.Account;

/**
 * 用户服务
 * Created by SHIKUN on 2016/10/29.
 */
public interface AccountService {
    Account register(Account userToAdd);

    String login(String username, String password);
//    //注册用户
//    public ResJsonTemplate addAccount(Account account);
//
//    //用户登录
//    public ResJsonTemplate checkAccount(Account account, String useragent, HttpServletRequest request);
//

}

