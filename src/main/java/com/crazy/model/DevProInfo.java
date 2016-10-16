package com.crazy.model;

import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/16.
 */
public class DevProInfo {
    private String dev_username;
    private Long enroll_project_id;
    private Date enroll_date;

    public String getDev_username() {
        return dev_username;
    }

    public void setDev_username(String dev_username) {
        this.dev_username = dev_username;
    }

    public Long getEnroll_project_id() {
        return enroll_project_id;
    }

    public void setEnroll_project_id(Long enroll_project_id) {
        this.enroll_project_id = enroll_project_id;
    }

    public Date getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        this.enroll_date = enroll_date;
    }

}
