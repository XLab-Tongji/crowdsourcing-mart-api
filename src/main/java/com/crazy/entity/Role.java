package com.crazy.entity;

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
    private Long role_id;
    private String role_name;

    public Role(Long role_id, String role_name) {
        this.role_id = role_id;
        this.role_name = role_name;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }
}
