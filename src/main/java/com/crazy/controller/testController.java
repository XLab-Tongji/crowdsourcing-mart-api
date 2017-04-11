package com.crazy.controller;

import com.crazy.JPA.AccountRepository;
import com.crazy.JPA.testRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jieping on 2017-04-09.
 */
@Controller
public class testController {
    @Autowired
    private testRepository testDao;
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping("/test")
    @ResponseBody
    public String getById(String username)
    {
     //  Account t= accountRepository.findByUsername(username);
       com.crazy.entity.test t = testDao.findByUsername(username);
        if(t!=null)
        {
            return t.toString();
        }
        return "not found test.id=";



    }
}
