package com.lab409.crowdingsourcing.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jieping on 2017-05-02.
 */
@Entity
@Table(name = "Requirement")
public class Requirement {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "requirement_name")
    private String requirementName;
    @Column(name = "requirement_type")
    private String requirementType;
    private Date startTime;
    private Date endTime;

    private int needManager;
    private String requirementDetail;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] file;
    private int requirementState = 0;
    private Long creatorId;
    private Long projectId;
    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public void setRequirementState(int requirementState) {
        this.requirementState = requirementState;
    }

    public int getRequirementState() {
        return requirementState;
    }

    public void setId(Long i)
    {
        id = i;
    }
    public Long getId()
    {
        return id;
    }
    public void setrequirementName(String r)
    {
        requirementName = r;
    }
    public String getrequirementName()
    {
        return requirementName;
    }
    public void setrequirementType(String t)
    {
        requirementType = t;
    }
    public String getrequirementType()
    {
        return requirementType;
    }
    public void setStartTime(Date s)
    {
        startTime = s;
    }
    public Date getStartTime()
    {
        return startTime;
    }
    public void setEndTime(Date e)
    {
        endTime = e;
    }
    public Date getEndTime()
    {
        return endTime;
    }
    public void setNeedManager(int n)
    {
        needManager = n;
    }
    public int getNeedManager()
    {
        return needManager;
    }
    public void setRequirementDetail(String d)
    {
        requirementDetail = d;
    }
    public String getRequirementDetail()
    {
        return requirementDetail;
    }
    public void setFile(byte[] f)
    {
        file = f;
    }
    public byte[] getFile()
    {
        return file;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
