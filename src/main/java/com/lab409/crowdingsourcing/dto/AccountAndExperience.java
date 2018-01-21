package com.lab409.crowdingsourcing.dto;

import com.lab409.crowdingsourcing.entity.ProjectExperience;

import java.util.List;


/**
 * @author yinghongcan
 */
public class AccountAndExperience {
    private String name;
    private Long accountId;
    private String email;
    private String mobile;
    private List<ProjectExperience> projectExperiences;

    public AccountAndExperience(String name, Long accountId, String email, String mobile, List<ProjectExperience> projectExperiences) {
        this.name = name;
        this.accountId = accountId;
        this.email = email;
        this.mobile = mobile;
        this.projectExperiences = projectExperiences;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<ProjectExperience> getProjectExperiences() {
        return projectExperiences;
    }

    public void setProjectExperiences(List<ProjectExperience> projectExperiences) {
        this.projectExperiences = projectExperiences;
    }
}
