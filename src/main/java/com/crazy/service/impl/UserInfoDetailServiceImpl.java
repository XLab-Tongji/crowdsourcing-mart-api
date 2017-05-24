package com.crazy.service.impl;

import com.crazy.entity.Account;
import com.crazy.entity.UserInfoDetail;
import com.crazy.repository.AccountRepository;
import com.crazy.repository.UserInfoDetailRepository;
import com.crazy.service.UserInfoDetailService;
import com.crazy.util.ResJsonTemplate;
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
