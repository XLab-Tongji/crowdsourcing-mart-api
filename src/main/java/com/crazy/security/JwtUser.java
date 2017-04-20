package com.crazy.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by yuhao on 2017/4/19.
 */
public class JwtUser implements UserDetails {
    private Long account_id;
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
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            Long account_id,
            String username,
            String password,
            String ext_params,
            String name,
            String icon,
            String email,
            Date create_time,
            Date update_time,
            String mobile,
            Long dev_id,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
        this.ext_params = ext_params;
        this.name = name;
        this.icon = icon;
        this.email = email;
        this.create_time = create_time;
        this.update_time = update_time;
        this.mobile = mobile;
        this.dev_id = dev_id;
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
}
