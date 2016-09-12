package com.crazy.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Entity;

import javax.persistence.*;

/**
 * Created by SHIKUN on 2016/9/9.
 */

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private boolean checked;
    @Column
    private String description;

    public Integer getId() {
        return id;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", checked=" + checked +
                ", description='" + description + '\'' +
                '}';
    }
}

