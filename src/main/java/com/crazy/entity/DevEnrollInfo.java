package com.crazy.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/10/16.
 */
@Entity
@Table(name="DevEnrollInfo")
//多主键标志
@IdClass(DevEnrollInfoPK.class)
public class DevEnrollInfo  {

    @Id
    @GeneratedValue
    private Long id;
    @Id
    private String username;
    @Id
    @Column(name = "project_id")
    private Long projectId;

    private Date enroll_date;
    public DevEnrollInfo( String username,  Long project_id)
    {
        this.username = username;
        this.projectId = project_id;
        enroll_date = new Date();
    }
    public Long getId()
    {
        return id;
    }
    public Long getProject_id() {
        return projectId;
    }

    public void setProject_id(Long project_id) {
        this.projectId = project_id;
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
                ", enroll_project_id=" + projectId +
                ", enroll_date=" + enroll_date +
                '}';
    }
}
