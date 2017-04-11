package com.crazy.repository;

import com.crazy.entity.AccountLogin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-10.
 */

/**
 * 对已经与数据库映射好的entity 进行数据操作
 * 直接使用JPA提供的方法，不需要自己实现，只需命名规则符合JPA
 */
public interface AccountLoginRepository extends CrudRepository<AccountLogin,Long> {
    public AccountLogin save(AccountLogin accountLogin);
    public AccountLogin findByToken(String token);
    
    public AccountLogin findById(Long id);
}
