package com.crazy.controller;

import com.crazy.entity.Account;
import com.crazy.repository.AccountRepository;
import com.crazy.security.JwtAuthenticationRequest;
import com.crazy.security.JwtAuthenticationResponse;
import com.crazy.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


/**
 * Account Controller
 * Created by SHIKUN on 2016/9/30.
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {
    @Value("Authorization")
    private String tokenHeader;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
        final String token = accountService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Account register(@RequestBody Account addedUser) throws AuthenticationException {
        return accountService.register(addedUser);
    }

    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Account getUserByUsername(@RequestParam(value = "username") String username) {
        return accountRepository.findByUsername(username);
    }


//
//    @Autowired
//    private AccountService accountService;
//
//
//    @Autowired
//    private Encryption encryption;
//
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    @ResponseBody
//    public ResJsonTemplate addAccount(@RequestBody Account account) {
//        return accountService.addAccount(account);
//    }
//
//
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public  ResJsonTemplate checkAccount(@RequestBody Account account, @RequestHeader(value = "User-Agent") String useragent,
//                                 HttpServletRequest request) {
//
//        return accountService.checkAccount(account, useragent, request);
//    }
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String test(@RequestParam(value = "token") String token) {
//        return encryption.tokenValidate(token);
//    }
}
