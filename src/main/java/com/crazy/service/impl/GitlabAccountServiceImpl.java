package com.crazy.service.impl;

import com.crazy.entity.Account;
import com.crazy.service.GitlabAccountService;
import com.crazy.util.ResJsonTemplate;
import org.springframework.stereotype.Service;
import com.crazy.util.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ni on 2016/11/9.
 */

@Service
public class GitlabAccountServiceImpl implements GitlabAccountService{
    static final String baseUrl = "http://115.159.55.131/api/v3";
    static final String private_token = "kbu9Hyz67Y5DRhYethcc";
    private HttpRequest httpRequest;

    @Override
    public boolean GitlabAddAccount(Account account) {
        String tail = "/users";
//        String email,String password,String usernanme,String name
        Map<String,String> param = new HashMap<>();
        param.put("email",account.getEmail());
        param.put("password",account.getPassword());
        param.put("username",account.getUsername());
        param.put("name",account.getName());
        try {
            httpRequest = new HttpRequest(baseUrl + tail);
            httpRequest.setPostParam(param,private_token);
            if(httpRequest.getResponseCode() != 201){
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public int getGitlabId(){
        try {
            return httpRequest.getGitlabId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
