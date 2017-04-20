package com.crazy.repository;

import com.crazy.entity.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-15.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleId(Long roleId);
}
