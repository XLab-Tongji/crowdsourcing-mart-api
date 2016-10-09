package com.crazy.model;

import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/9.
 */
public class AccountLogin {
    private String ip;
    private String device;
    private String token;
    private Date create_time;
    private Date expire_time;
    private Long account_id;
    private String plat;
    private Long id;

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
                '}';
    }
}
