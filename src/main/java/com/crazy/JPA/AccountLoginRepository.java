package com.crazy.JPA;

import com.crazy.entity.AccountLogin;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-10.
 */
public interface AccountLoginRepository extends CrudRepository<AccountLogin,Long> {
    public AccountLogin save(AccountLogin accountLogin);
}
