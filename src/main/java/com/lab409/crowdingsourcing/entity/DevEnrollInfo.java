package com.lab409.crowdingsourcing.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/16.
 */
@Entity
@Table(name = "DevEnrollInfo")
public class DevEnrollInfo {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private Long projectId;
    private Date enrollDate;

    public DevEnrollInfo(String username, Long project_id) {
        this.username = username;
        this.projectId = project_id;
        enrollDate = new Date();
    }

    public DevEnrollInfo() {
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "DevEnrollInfo{" +
                "username='" + username + '\'' +
                ", enroll_project_id=" + projectId +
                ", enroll_date=" + enrollDate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    public Long getProjectId(){
        return this.projectId;
    }
    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }
    public Date getEnrollDate(){
        return this.enrollDate;
    }
}
