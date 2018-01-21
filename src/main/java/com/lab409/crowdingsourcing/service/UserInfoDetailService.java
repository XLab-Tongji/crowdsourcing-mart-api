package com.lab409.crowdingsourcing.service;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.UserInfoDetail;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;

/**
 * Created by jieping on 2017-05-24.
 */
public interface UserInfoDetailService {
    public ResJsonTemplate addUserInfoDetail(Account account, UserInfoDetail userInfoDetail);
}
