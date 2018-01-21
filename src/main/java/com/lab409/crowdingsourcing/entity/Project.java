package com.lab409.crowdingsourcing.entity;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long projectId;

    private double cost;

    private int deliveryCycle;

    private int warrantyCycle;

    private Date createDate;

    private String address;

    private String description;

    private File file;

    private String projectType;

    private String username;

    private String projectName;

    private Date enrollStopTime;

    private Date updateDate;

    //1为申请中，2为开发中，3为已完成
    private int projectState;

    public Project() {
    }

    public Project(double cost, Integer devlivery_cycle, Integer warranty_cycle,
                   String address, String description, String username,
                   String project_type, String project_name) {
        this.cost = cost;
        this.deliveryCycle = devlivery_cycle;
        this.warrantyCycle = warranty_cycle;
        this.address = address;
        this.description = description;
        this.username = username;
        this.projectType = project_type;
        this.projectName = project_name;
    }

    public Long getprojectId() {
        return projectId;
    }

    public void setProject_id(Long project_id) {
        this.projectId = project_id;
    }

    public Date getEnrollStopTime() {
        return enrollStopTime;
    }

    public void setEnrollStopTime(Date enrollStopTime) {
        this.enrollStopTime = enrollStopTime;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDeliveryCycle() {
        return deliveryCycle;
    }

    public void setDeliveryCycle(int deliveryCycle) {
        this.deliveryCycle = deliveryCycle;
    }

    public int getWarrantyCycle() {
        return warrantyCycle;
    }

    public void setWarrantyCycle(int warrenty_cycle) {
        this.warrantyCycle = warrenty_cycle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public int getProjectState() {
        return projectState;
    }

    public void setProjectState(int projectState) {
        this.projectState = projectState;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + projectId +
                ", cost=" + cost +
                ", deliveryCycle=" + deliveryCycle +
                ", warrantyCycle=" + warrantyCycle +
                ", createDate=" + createDate +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", file=" + file +
                ", projectType='" + projectType + '\'' +
                ", username='" + username + '\'' +
                ", projectName='" + projectName + '\'' +
                ", enrollStopTime=" + enrollStopTime +
                ", updateDate=" + updateDate +
                '}';
    }
}
