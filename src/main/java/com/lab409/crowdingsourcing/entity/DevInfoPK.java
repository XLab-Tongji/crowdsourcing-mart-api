package com.lab409.crowdingsourcing.entity;

import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by jieping on 2017-04-11.
 */

/**
 * 多主键需要实现 Serializable
 * 里面存放所需主键
 * 并且实现equals 和 hashCode
 */
@IdClass(DevInfoPK.class)
public class DevInfoPK implements Serializable{
    private Long id;

    private String username;

    private Long projectId;
    public Long getId()
    {
        return id;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public void setProject_id(Long project_id)
    {
        this.projectId = project_id;
    }
    public Long getProject_id()
    {
        return projectId;
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof DevInfoPK){

            DevInfoPK devEnrollInfoPK = (DevInfoPK)obj;

            if(this.projectId == devEnrollInfoPK.getProject_id() && this.id==devEnrollInfoPK.getId()&&this.username.equals(devEnrollInfoPK.getUsername())){

                return true;

            }
        }

        return false;

    }
    public int hashCode()
    {
        return  super.hashCode();
    }
}
