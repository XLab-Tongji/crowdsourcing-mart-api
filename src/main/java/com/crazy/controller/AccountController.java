package com.crazy.controller;

import com.crazy.entity.Account;
import com.crazy.service.AccountService;
import com.crazy.util.Encryption;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**Account Controller
 * Created by SHIKUN on 2016/9/30.
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;


    @Autowired
    private Encryption encryption;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResJsonTemplate addAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public  ResJsonTemplate checkAccount(@RequestBody Account account, @RequestHeader(value = "User-Agent") String useragent,
                                 HttpServletRequest request) {

        return accountService.checkAccount(account, useragent, request);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam(value = "token") String token) {
        return encryption.tokenValidate(token);
    }




}
