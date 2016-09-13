package com.crazy.controller;

import com.crazy.mapper.UserMapper;
import com.crazy.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by SHIKUN on 2016/9/13.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int addUser(@RequestBody Users users) {
        return userMapper.addUser(users.getUser_name(), users.getPhone(), users.getEmail(), users.getPassword(), users.getAddress(),
                users.getQq());

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Users> searchAllUsers() {
        return userMapper.searchAll();
    }


}

