package com.crazy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jieping on 2017-04-06.
 */
@Entity
@Table(name="test")
public class test {


    public test()
    {

    }

    @Id
    @GeneratedValue
    long id;
    String username;
    public void setId(long id)
    {
        this.id = id;
    }
    public long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }

    @Override
    public  String toString()
    {
        return "id:" + id ;
    }





}
