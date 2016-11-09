package com.crazy.entity;

import java.util.Date;

/**developer domain
 * Created by SHIKUN on 2016/10/15.
 */
public class Developer {

    private String username;
    private Long account_id;
    private String dev_domain;
    private String dev_intro;
    private String ext_param;
    private Date Enroll_date;

    public Date getEnroll_date() {
        return Enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        Enroll_date = enroll_date;
    }

    public String getDev_intro() {
        return dev_intro;
    }

    public void setDev_intro(String dev_intro) {
        this.dev_intro = dev_intro;
    }

    public String getExt_param() {
        return ext_param;
    }

    public void setExt_param(String ext_param) {
        this.ext_param = ext_param;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public String getDev_domain() {
        return dev_domain;
    }

    public void setDev_domain(String dev_domain) {
        this.dev_domain = dev_domain;
    }

    public String getDev_project() {
        return dev_intro;
    }

    public void setDev_project(String dev_project) {
        this.dev_intro = dev_project;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "username='" + username + '\'' +
                ", account_id=" + account_id +
                ", dev_domain='" + dev_domain + '\'' +
                ", dev_intro='" + dev_intro + '\'' +
                ", ext_param='" + ext_param + '\'' +
                ", Enroll_date=" + Enroll_date +
                '}';
    }
}
