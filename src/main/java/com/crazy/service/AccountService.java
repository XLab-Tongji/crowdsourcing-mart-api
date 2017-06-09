package com.crazy.service;

import com.crazy.entity.Account;

import java.util.HashMap;

/**
 * 用户服务
 * Created by SHIKUN on 2016/10/29.
 */
public interface AccountService {
    Account register(Account userToAdd);

    String login(String username, String password);

    HashMap GetRequirementDetail(String username, Long requirementId, int state);
}

