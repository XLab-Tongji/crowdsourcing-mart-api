package com.lab409.crowdingsourcing.service.impl;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.UserInfoDetail;
import com.lab409.crowdingsourcing.repository.AccountRepository;
import com.lab409.crowdingsourcing.repository.UserInfoDetailRepository;
import com.lab409.crowdingsourcing.service.UserInfoDetailService;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jieping on 2017-05-24.
 */
@Service
public class UserInfoDetailServiceImpl implements UserInfoDetailService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserInfoDetailRepository userInfoDetailRepository;
    @Override
    public ResJsonTemplate addUserInfoDetail(Account account, UserInfoDetail userInfoDetail) {

        account.setInfo_id(userInfoDetailRepository.save(userInfoDetail).getId());
        accountRepository.save(account);
        return new ResJsonTemplate("200", "实名认证成功");
    }
}
