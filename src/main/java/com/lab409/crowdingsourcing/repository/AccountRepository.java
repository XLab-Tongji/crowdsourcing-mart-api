package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-10.
 */

/**
 * 为数据库映射后的account数据操作
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
    /**
     * @param account 要存储的account
     * @return 返回已经存储在数据库的account
     */
    @Override
    Account save(Account account);

    /**
     * @param username
     * @return 返回符合username的account对象
     */
    Account findByUsername(String username);

    List<Account> findAll();
}
