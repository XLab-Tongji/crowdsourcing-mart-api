package com.crazy.controller;

import com.crazy.mapper.AccountMapper;
import com.crazy.model.Account;

import com.crazy.model.Developer;
import com.crazy.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**Account Controller
 * Created by SHIKUN on 2016/9/30.
 */

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ConvertJson convertJson;

    @Autowired
    private Encryption encryption;

    @Autowired
    private DateUtil dateUtil;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    ResJsonTemplate addAccount(@RequestBody Account account) {

        System.out.println(account.getExt_params());

        return new ResJsonTemplate("200", accountMapper.addAcount(account.getUsername(),
                account.getName(), account.getIcon(),
                encryption.doEncryption(account.getPassword()),
                account.getMobile(), account.getCreate_time(),
                account.getUpdate_time(), account.getEmail(),
                convertJson.Map2Json(account.getExt_params())));
    }

    @RequestMapping(value = "/list/username", method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getAllUsername() throws Exception {
        ResJsonTemplate resJsonTemplate = new ResJsonTemplate("200", accountMapper.getAllUsername());
        return resJsonTemplate;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate checkAccount(@RequestBody Account account, @RequestHeader(value = "User-Agent") String useragent,
                                 HttpServletRequest request) {

        String result = null;
        String token = null;
        Map<String, String> selectResult = accountMapper.getCheckInfo(account.getUsername());

        if (selectResult == null) {
            result = "没有此用户";
        } else if (!encryption.checkPassword(account.getPassword(), selectResult.get("password"))) {
            result = "密码错误";
        } else {
            token = encryption.createToken();
            accountMapper.addLoginLog(request.getRemoteAddr(), token, dateUtil.Str2Date(dateUtil.getNowTime()),
                    dateUtil.Str2Date(dateUtil.setExpire(30)), accountMapper.getUserId(account.getUsername()),
                    useragent, account.getUsername());
            result = token;
        }
        return new ResJsonTemplate("200", token);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(value = "token") String token) {
        return encryption.tokenValidate(token);
    }

    @RequestMapping(value = "developer/add", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate devInfoadd(@RequestBody Developer developer) {

        return new ResJsonTemplate("200", accountMapper.addDevInfo(developer.getUsername(),
                accountMapper.getUserId(developer.getUsername()), developer.getDev_domain(),
                developer.getDev_project(), developer.getProject_enroll()));

    }
    


}
