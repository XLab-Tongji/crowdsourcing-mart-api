package com.lab409.crowdingsourcing.service.impl;

import com.lab409.crowdingsourcing.dto.AccountDetail;
import com.lab409.crowdingsourcing.dto.AccountInfo;
import com.lab409.crowdingsourcing.dto.LoginAccountDto;
import com.lab409.crowdingsourcing.dto.SimpleAccountDto;
import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import com.lab409.crowdingsourcing.entity.Requirement;
import com.lab409.crowdingsourcing.entity.UserInfoDetail;
import com.lab409.crowdingsourcing.repository.AccountRepository;
import com.lab409.crowdingsourcing.repository.DevEnrollInfoRepository;
import com.lab409.crowdingsourcing.repository.RequirementRepository;
import com.lab409.crowdingsourcing.repository.UserInfoDetailRepository;
import com.lab409.crowdingsourcing.service.AccountService;
import com.lab409.crowdingsourcing.util.MD5Util;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yinghongcan
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UserInfoDetailRepository userInfoDetailRepository;

    @Autowired
    private RequirementRepository requirementRepository;

    @Autowired
    private DevEnrollInfoRepository devEnrollInfoRepository;

    @Override
    public ResJsonTemplate updateInfo(Account account) {
        accountRepository.save(account);
        return new ResJsonTemplate<>("200","修改成功");
    }

    @Override
    public ResJsonTemplate register(LoginAccountDto loginAccountDto) {
        if(accountRepository.findByUsername(loginAccountDto.getUsername()) != null) {
            return new ResJsonTemplate<>("400","账号已存在");
        }
        else{
            Account account = new Account();
            account.setEmail(loginAccountDto.getEmail());
            account.setPassword(MD5Util.md5(loginAccountDto.getPassword()));
            account.setUsername(loginAccountDto.getUsername());
            System.out.println(account.toString());
            accountRepository.save(account);
            return new ResJsonTemplate<>("201","注册成功");
        }
    }
    /**
     * 传入accountInfo，即username&password后返回token
     * @return token
     */
    @Override
    public ResJsonTemplate<Object> login(AccountInfo accountInfo) {
        Account account = accountRepository.findByUsername(accountInfo.getUsername());
        if (account == null) {
            return new ResJsonTemplate<>("404", "用户名不存在");
        }
//        System.out.println(account.getPassword() + "  " + accountInfo.toString());
        /**
         * md5对密码加密
         */
        accountInfo.setPassword(MD5Util.md5(accountInfo.getPassword()));
        if (account.getPassword().equals(accountInfo.getPassword())) {
            String tokenId = MD5Util.md5((accountInfo.getPassword() + accountInfo.getUsername() + System.currentTimeMillis()));
            stringRedisTemplate.opsForValue().set(tokenId, account.getUsername());
            AccountDetail accountDetail = new AccountDetail();
            accountDetail.setTokenid(tokenId);
            UserInfoDetail userInfoDetail = userInfoDetailRepository.findById(account.getInfo_id());
            String realName;
            if(userInfoDetail == null) {
                realName = null;
            }
            else{
                realName = userInfoDetail.getRealname();
            }
            accountDetail.setRealname(realName);
            accountDetail.setEmail(account.getEmail());
            accountDetail.setMobile(account.getMobile());
            accountDetail.setUsername(account.getUsername());
            return new ResJsonTemplate<>("200",accountDetail);
        }
        return new ResJsonTemplate<>("404","unauthorized");
    }

    @Override
    public Account getAccount(HttpServletRequest request)
    {
        java.lang.String token = request.getHeader("Authorization");
        Boolean tokenExist = stringRedisTemplate.hasKey(token);
        if (tokenExist) {
            java.lang.String username = stringRedisTemplate.opsForValue().get(token);
            return accountRepository.findByUsername(username);
        }
        else {
            return null;
        }
    }



    @Override
    public HashMap GetRequirementDetail(String username, Long requirementId, int state) {
        Requirement requirement = requirementRepository.findOne(requirementId);
        HashMap<String, Object> result = new HashMap<>();
        result.put("requirement_name ", requirement.getrequirementName());
        result.put("requirement_type", requirement.getrequirementType());
        result.put("start_time", requirement.getStartTime());
        result.put("end_time", requirement.getEndTime());
        result.put("requirement_detail", requirement.getRequirementDetail());
        if (state == 0) {
            return result;
        }
        List<SimpleAccountDto> developers = new ArrayList<>();
        List<DevEnrollInfo> devEnrollInfo = new ArrayList<>();
        devEnrollInfo = devEnrollInfoRepository.findByProjectId(requirementId);
        if (state == 1) {
            for (DevEnrollInfo aDevEnrollInfo : devEnrollInfo) {
                if (aDevEnrollInfo.getUsername().equals(username)) {
                    result.put("is_enroll", true);
                    return result;
                }
            }
            result.put("is_enroll", false);
            return result;
        } else {

            for (DevEnrollInfo aDevEnrollInfo : devEnrollInfo) {
                String userName = aDevEnrollInfo.getUsername();
                Account account = accountRepository.findByUsername(userName);
                SimpleAccountDto simpleAccount = new SimpleAccountDto();
                simpleAccount.setEmail(account.getEmail());
                simpleAccount.setName(account.getName());
                simpleAccount.setPhoneNumber(account.getMobile());
                developers.add(simpleAccount);
            }

            result.put("need_manager", requirement.getNeedManager());
            result.put("file", requirement.getFile());
            result.put("DeveloperDto", developers);
            return result;
        }
    }

}
