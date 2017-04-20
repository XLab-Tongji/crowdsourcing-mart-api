package com.crazy.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/**
 * Created by SHIKUN on 2016/9/29.
 */

/**
 * Account 与 数据库ACCOUT映射
 */
@Entity
@Table(name = "Account")
public class Account implements UserDetails {

    @Id
    @GeneratedValue
    private Long account_id;
    @Column(name = "username",unique = true)
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
    @Transient
    private  Collection<GrantedAuthority> authorities;
    public Account()
    {
        List<GrantedAuthority> authties = new ArrayList<GrantedAuthority>();
        authties.add(new SimpleGrantedAuthority("user"));
    }
    public Account(String username, String password, String ext_params, String name, String icon, String email, String mobile,Long role_id) {
        this.username = username;
        this.password = password;
        this.ext_params = ext_params;
        this.name = name;
        this.icon = icon;
        this.email = email;
        this.mobile = mobile;
        this.role_id = role_id;
        SearchRole searchRole = new SearchRole();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(searchRole.getRoleName(this.getRole_id()).getRole_name()));
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        Long t = this.getRole_id();
        String roleName = t==1?"user":"admin";
        auths.add(new SimpleGrantedAuthority(roleName));

        return auths;
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
    @Override
    public String toString()
    {
        return "Account{" +
                "account_id=" + account_id.toString() +
                ", username=" + username +
                ", roleId=" + role_id.toString()  +
                ", dev_id=" + dev_id.toString() +
                ", email=" + email   +
                ", create_time=" + create_time.toString() +
                ", update_time=" + update_time.toString() +
                ", mobile=" + mobile +
                ", name=" + name +
                ", ext_params=" +ext_params+
                ", icon" + icon +
                '}';
    }

}











