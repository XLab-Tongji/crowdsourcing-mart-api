package com.crazy.service;

import com.crazy.entity.Account;

/**
 * Created by Ni on 2016/11/9.
 */
public interface GitlabAccountService {

    //调用gitlab API添加账号
    boolean GitlabAddAccount(Account account);

    //获取gitlabId
    int getGitlabId();
}
