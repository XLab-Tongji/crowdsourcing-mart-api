package com.crazy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by yuhao on 2017/4/6.
 */
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @Column(name = "role_id")
    private Long roleId;
    private String role_name;
    public Role()
    {

    }
    public Role(Long role_id, String role_name) {
        this.roleId = role_id;
        this.role_name = role_name;
    }

    public Long getRole_id() {
        return roleId;
    }

    public void setRole_id(Long role_id) {
        this.roleId = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
