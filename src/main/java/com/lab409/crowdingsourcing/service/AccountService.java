package com.lab409.crowdingsourcing.service;

import com.lab409.crowdingsourcing.dto.AccountInfo;
import com.lab409.crowdingsourcing.dto.LoginAccountDto;
import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public interface AccountService {
    /**
     * TODO
     * 加密功能 密码在数据库里应该存成哈希
     */
    public ResJsonTemplate updateInfo(Account account);

    public ResJsonTemplate register(LoginAccountDto loginAccountDto);

    public ResJsonTemplate<Object> login(AccountInfo accountInfo);

    public Account getAccount(HttpServletRequest request);

    public HashMap GetRequirementDetail(String username, Long requirementId, int state);

}
