package com.crazy.JPA;

import com.crazy.entity.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-10.
 */

/**
 * 为数据库映射后的account数据操作
 */
public interface AccountRepository extends CrudRepository<Account,Long> {
    /**
     *
     * @param account 要存储的account
     * @return 返回已经存储在数据库的account
     */
    public Account save(Account account);

    /**
     *
     * @param username
     * @return 返回符合username的account对象
     */
    public Account findByUsername(String username);


}
