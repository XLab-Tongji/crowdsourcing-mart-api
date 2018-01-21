package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.UserInfoDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-05-01.
 */
public interface UserInfoDetailRepository extends CrudRepository<UserInfoDetail, Long> {
    UserInfoDetail findById(Long id);
    UserInfoDetail save(UserInfoDetail u);
}
