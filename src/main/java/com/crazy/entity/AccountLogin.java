package com.crazy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/9.
 */
@Entity
@Table(name="ACCOUNT_LOGIN_LOG")
public class AccountLogin {
    @Id
    @GeneratedValue
    private Long id;
    private String ip;

    private String device;

    private String token;

    private Date create_time;

    private Date expire_time;

    private Long account_id;

    private String plat;



    private String username;
    public AccountLogin()
    {}
    public AccountLogin( String ip, String token,  Date create_time,
                         Date expire_time,  Long account_id,
                        String plat,  String username)
    {
        this.ip = ip;
        this.token = token;
        this.create_time = create_time;
        this.expire_time = expire_time;
        this.account_id = account_id;
        this.plat = plat;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(Date expire_time) {
        this.expire_time = expire_time;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    @Override
    public String toString() {
        return "AccountLogin{" +
                "ip='" + ip + '\'' +
                ", device='" + device + '\'' +
                ", token='" + token + '\'' +
                ", create_time=" + create_time +
                ", expire_time=" + expire_time +
                ", account_id=" + account_id +
                ", plat='" + plat + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
