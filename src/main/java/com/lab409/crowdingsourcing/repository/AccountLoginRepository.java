package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.AccountLogin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-10.
 */

/**
 * 对已经与数据库映射好的entity 进行数据操作
 * 直接使用JPA提供的方法，不需要自己实现，只需命名规则符合JPA
 */
public interface AccountLoginRepository extends CrudRepository<AccountLogin, Long> {
    AccountLogin save(AccountLogin accountLogin);

    AccountLogin findByToken(String token);

    AccountLogin findById(Long id);
}
