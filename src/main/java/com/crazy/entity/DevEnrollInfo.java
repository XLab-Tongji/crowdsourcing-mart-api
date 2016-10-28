package com.crazy.entity;

import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/16.
 */
public class DevEnrollInfo {

    private String username;

    private Long project_id;

    private Date enroll_date;


    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getEnroll_date() {
        return enroll_date;
    }

    public void setEnroll_date(Date enroll_date) {
        this.enroll_date = enroll_date;
    }

    @Override
    public String toString() {
        return "DevEnrollInfo{" +
                "username='" + username + '\'' +
                ", enroll_project_id=" + project_id +
                ", enroll_date=" + enroll_date +
                '}';
    }
}
