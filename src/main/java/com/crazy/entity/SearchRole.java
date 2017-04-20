package com.crazy.entity;

import com.crazy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jieping on 2017-04-15.
 */
public class SearchRole {
   @Autowired
    private  RoleRepository roleRepository;
    public Role getRoleName(Long roleId)
    {
        Role temp = roleRepository.findByRoleId(roleId);
        return roleRepository.findByRoleId(roleId);
    }

}
