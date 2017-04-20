package com.crazy.service.impl;

import com.crazy.entity.Account;
import com.crazy.entity.AccountLogin;
import com.crazy.repository.AccountLoginRepository;
import com.crazy.repository.AccountRepository;
import com.crazy.service.AccountService;
import com.crazy.service.GitlabAccountService;
import com.crazy.util.ConvertJson;
import com.crazy.util.DateUtil;
import com.crazy.util.Encryption;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 * Created by SHIKUN on 2016/10/29.
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountLoginRepository accountLoginRepository;


    @Autowired
    private Encryption encryption;

    @Autowired
    private ConvertJson convertJson;

    @Autowired
    private DateUtil dateUtil;

    @Autowired
    private GitlabAccountService gitlabAccountService;

    @Override
    public ResJsonTemplate addAccount(Account account) {
        try {
            Account savedAccount = new Account(account.getUsername(), encryption.doEncryption(account.getPassword()), account.getExt_params(),
                    account.getName(),
                    account.getIcon(), account.getEmail(),
                    account.getMobile(), 1L);
            Account temp = accountRepository.save(savedAccount);
            return new ResJsonTemplate("200", temp);
            /*
            return new ResJsonTemplate("200",accountMapper.addAcount(account.getUsername(), account.getName(), account.getIcon(),
                    encryption.doEncryption(account.getPassword()),
                    account.getMobile(), account.getEmail(),
                    account.getExt_params()
            ));
            */
            /*
            return new ResJsonTemplate("200",accountMapper.addAcount(account.getUsername(), account.getName(), account.getIcon(),
                    encryption.doEncryption(account.getPassword()),
                    account.getMobile(), account.getEmail(),
                    convertJson.Map2Json(account.getExt_params())
            ));
            */

            /*if (!gitlabAccountService.GitlabAddAccount(account)) {
                return new ResJsonTemplate("500", "创建gitlab用户失败");
            }

            int ret = accountMapper.addAcount(account.getUsername(), account.getName(), account.getIcon(),
                    encryption.doEncryption(account.getPassword()),
                    account.getMobile(), account.getEmail(),
                    convertJson.Map2Json(account.getExt_params()));
            System.out.println(ret);
            System.out.println(gitlabAccountService.getGitlabId());

            martGitConnectionMapper.addConnection(ret, gitlabAccountService.getGitlabId());

            return new ResJsonTemplate("200", ret);*/

        } catch (Exception ex) {
            System.out.println(ex);

            return new ResJsonTemplate("500", "创建用户失败");

        }
    }


    @Override
    public ResJsonTemplate checkAccount(Account account, String useragent, HttpServletRequest request) {

        String result = null;
        String token = null;
        String tmep = account.getUsername();
        Account selectResult = accountRepository.findByUsername(account.getUsername());
        //     Map<String, String> selectResult = accountMapper.getCheckInfo(account.getUsername());

        if (selectResult == null) {
            result = "没有此用户";
        } else if (!encryption.checkPassword(account.getPassword(), selectResult.getPassword())) {
            result = "密码错误";
        } else {
            token = encryption.createToken();
            /*
            accountMapper.addLoginLog(request.getRemoteAddr(), token, dateUtil.Str2Date(dateUtil.getNowTime()),
                    dateUtil.Str2Date(dateUtil.setExpire(30)), accountMapper.getUserId(account.getUsername()),
                    useragent, account.getUsername());
            */
            accountLoginRepository.save(new AccountLogin(request.getRemoteAddr(), token, dateUtil.Str2Date(dateUtil.getNowTime()),
                    dateUtil.Str2Date(dateUtil.setExpire(30)), selectResult.getAccount_id(),
                    useragent, selectResult.getUsername()));

            result = token;
        }

        if (token != null) {
            Map tokencons = new HashMap();

            tokencons.put("tokens", token);
            tokencons.put("username", account.getUsername());

            List<Map> tokenresult = new LinkedList<>();

            tokenresult.add(tokencons);

            return new ResJsonTemplate("200", tokenresult);
        } else {
            return new ResJsonTemplate("200", result);
        }

    }
}
