package com.crazy.controller;

import com.crazy.mapper.AccountMapper;
import com.crazy.entity.Account;

import com.crazy.service.AccountService;
import com.crazy.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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
