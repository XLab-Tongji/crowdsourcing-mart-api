package com.crazy.service;

import com.crazy.entity.Account;

/**
 * Created by jieping on 2017-04-17.
 */
public interface AuthService {
    Account register(Account userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
