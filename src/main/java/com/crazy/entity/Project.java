package com.crazy.entity;

import javax.persistence.*;
import java.io.File;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@Entity
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long projectId;

    private double cost;

    private int delivery_cycle;

    private int warranty_cycle;

    private Date create_date;

    private String address;

    private String description;

    private File file;

    private String project_type;

    private String username;

    private String project_name;

    private Date enroll_stop_time;

    private Date update_date;
    public  Project()
    {}
    public  Project( double cost,  Integer devlivery_cycle,  Integer warranty_cycle,
                     String address, String description,  String username,
                     String project_type,  String project_name)
    {
        this.cost = cost;
        this.delivery_cycle = devlivery_cycle;
        this.warranty_cycle = warranty_cycle;
        this.address = address;
        this.description = description;
        this.username = username;
        this.project_type = project_type;
        this.project_name = project_name;
    }
    public Long getprojectId() {
        return projectId;
    }

    public void setProject_id(Long project_id) {
        this.projectId = project_id;
    }

    public Date getEnroll_stop_time() {
        return enroll_stop_time;
    }

    public void setEnroll_stop_time(Date enroll_stop_time) {
        this.enroll_stop_time = enroll_stop_time;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public int getDelivery_cycle() {
        return delivery_cycle;
    }

    public void setDelivery_cycle(int delivery_cycle) {
        this.delivery_cycle = delivery_cycle;
    }

    public int getWarranty_cycle() {
        return warranty_cycle;
    }

    public void setWarranty_cycle(int warrenty_cycle) {
        this.warranty_cycle = warrenty_cycle;
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    @Override
    public String toString() {
        return "Project{" +
                "project_id=" + projectId +
                ", cost=" + cost +
                ", delivery_cycle=" + delivery_cycle +
                ", warranty_cycle=" + warranty_cycle +
                ", create_date=" + create_date +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", file=" + file +
                ", project_type='" + project_type + '\'' +
                ", username='" + username + '\'' +
                ", project_name='" + project_name + '\'' +
                ", enroll_stop_time=" + enroll_stop_time +
                ", update_date=" + update_date +
                '}';
    }
}
