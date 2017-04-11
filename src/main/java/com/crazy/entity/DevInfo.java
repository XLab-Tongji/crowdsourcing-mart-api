package com.crazy.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/26.
 */
@Entity
@Table(name="developing_info")
@IdClass(DevInfoPK.class)
public class DevInfo {
    @Id
    @GeneratedValue
    private Long id;
    @Id
    private String username;
    @Id
    @Column(name = "project_id")
    private Long projectId;

    private Date confirm_date;
    public DevInfo(String username,Long project_id)
    {
        this.username = username;
        this.projectId = project_id;
        confirm_date = new Date();
    }
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
        return projectId;
    }

    public void setProject_id(Long project_id) {
        this.projectId = project_id;
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
                ", project_id=" + projectId +
                ", confirm_date=" + confirm_date +
                '}';
    }
}
