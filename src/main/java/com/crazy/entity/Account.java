package com.crazy.entity;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by SHIKUN on 2016/9/29.
 */

/**
 * Account 与 数据库ACCOUT映射
 */
@Entity
@Table(name = "Account")
public class Account {

    @Id
    @Column(nullable = false)
    @GeneratedValue
    private Long account_id;
<<<<<<< HEAD
    @Column(name = "username", unique = true)
=======
    @Column(name = "username",unique = true)
>>>>>>> af87fc58949c25d851e0cf7ae94f0395f89f6475
    private String username;
    private String password;
    private String ext_params;

    private String name;

    private String icon;


    private String email;

    private Date create_time;

    private Date update_time;


    private String mobile;

    private Long dev_id;


    private Long role_id;

    public Account() {

    }


    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExt_params() {
        return ext_params;
    }

    public void setExt_params(String ext_params) {
        this.ext_params = ext_params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getDev_id() {
        return dev_id;
    }

    public void setDev_id(Long dev_id) {
        this.dev_id = dev_id;
    }

    public Long getRole_id() {
        return role_id;
    }

    public void setRole_id(Long role_id) {
        this.role_id = role_id;
    }
}











