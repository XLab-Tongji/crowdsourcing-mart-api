package com.crazy.service;

import com.crazy.entity.Account;
import com.crazy.entity.UserInfoDetail;
import com.crazy.util.ResJsonTemplate;

/**
 * Created by jieping on 2017-05-24.
 */
public interface UserInfoDetailService {
    public ResJsonTemplate addUserInfoDetail(Account account, UserInfoDetail userInfoDetail);
}
