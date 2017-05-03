package com.crazy.repository;

import com.crazy.entity.UserInfoDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-05-01.
 */
public interface UserInfoDetailRepository extends CrudRepository<UserInfoDetail, Long> {
    UserInfoDetail findById(Long id);
    UserInfoDetail save(UserInfoDetail u);
}
