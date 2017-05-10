package com.crazy.entity;

import javax.persistence.*;

/**
 * Created by jieping on 2017-05-10.
 */
@Entity
public class ProjectExperience {
    @Id
    @GeneratedValue
    private Long id;

    private Long accountId;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] certificate;
    private String projectName;
    private String projectRegion;
    private String projectAddress;
    private String projectText;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRegion() {
        return projectRegion;
    }

    public void setProjectRegion(String projectRegion) {
        this.projectRegion = projectRegion;
    }

    public String getProjectAddress() {
        return projectAddress;
    }

    public void setProjectAddress(String projectAddress) {
        this.projectAddress = projectAddress;
    }

    public String getProjectText() {
        return projectText;
    }

    public void setProjectText(String projectText) {
        this.projectText = projectText;
    }
}
