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
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private double cost;
    @Column
    private int delivery_cycle;
    @Column
    private int warranty_cycle;
    @Column
    private String address;
    @Column
    private String description;
    @Column
    private String username;
    @Column
    private File file;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



}
