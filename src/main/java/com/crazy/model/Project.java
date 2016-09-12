package com.crazy.model;

import org.hibernate.annotations.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.File;

/**
 * Created by SHIKUN on 2016/9/12.
 */
public class Project {

    private Long id;

    private double cost;

    private int delivery_cycle;

    private int warranty_cycle;

    private String address;

    private String description;

    private String user_name;

    private File file;

    private String project_type;

    private String dev_user_name;

    public String getDev_user_name() {
        return dev_user_name;
    }

    public void setDev_user_name(String dev_user_name) {
        this.dev_user_name = dev_user_name;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }
}
