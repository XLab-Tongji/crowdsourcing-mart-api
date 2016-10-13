package com.crazy.model;

import org.hibernate.annotations.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;
import java.util.Date;

/**
 * Created by SHIKUN on 2016/9/12.
 */
public class Project {

    private Long id;

    private double cost;

    private int delivery_cycle;

    private int warranty_cycle;

    private Date create_date;

    private String address;

    private String description;

    private String project_user_name;

    private File file;

    private String project_type;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getProject_user_name() {
        return project_user_name;
    }

    public void setProject_user_name(String project_user_name) {
        this.project_user_name = project_user_name;
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
}
