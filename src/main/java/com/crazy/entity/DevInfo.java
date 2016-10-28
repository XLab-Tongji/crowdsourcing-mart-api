package com.crazy.entity;

import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/26.
 */
public class DevInfo {

    private Long id;

    private String username;

    private Long project_id;

    private Date confirm_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public Date getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(Date confirm_date) {
        this.confirm_date = confirm_date;
    }

    @Override
    public String toString() {
        return "DevInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", project_id=" + project_id +
                ", confirm_date=" + confirm_date +
                '}';
    }
}
