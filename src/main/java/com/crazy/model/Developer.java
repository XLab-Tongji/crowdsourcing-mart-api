package com.crazy.model;

import java.util.List;
import java.util.Map;

/**developer domain
 * Created by SHIKUN on 2016/10/15.
 */
public class Developer {

    private String username;
    private Long account_id;
    private String dev_domain;
    private String dev_project;
    private List project_enroll;
    private Long dev_project_id;

    public Long getDev_project_id() {
        return dev_project_id;
    }

    public void setDev_project_id(Long dev_project_id) {
        this.dev_project_id = dev_project_id;
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
        return dev_project;
    }

    public void setDev_project(String dev_project) {
        this.dev_project = dev_project;
    }

    public List getProject_enroll() {
        return project_enroll;
    }

    public void setProject_enroll(List project_enroll) {
        this.project_enroll = project_enroll;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "username='" + username + '\'' +
                ", account_id=" + account_id +
                ", dev_domain='" + dev_domain + '\'' +
                ", dev_project='" + dev_project + '\'' +
                ", project_enroll='" + project_enroll + '\'' +
                '}';
    }
}
