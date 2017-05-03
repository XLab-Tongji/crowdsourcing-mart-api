package com.crazy.entity;

import javax.persistence.*;

/**
 * Created by jieping on 2017-05-01.
 */
@Entity
@Table(name = "UserInfoDetail")
public class UserInfoDetail {
    @Id
    @GeneratedValue
    private Long id;
    private String  realname;
    private int gender;
    private String profession;
    private String idcard;
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public String getRealname()
    {
        return realname;
    }
    public void setRealname(String rn)
    {
        realname = rn;
    }
    public int getGender()
    {
        return gender;
    }
    public void setGender(int g)
    {
        gender = g;
    }
    public String getProfession()
    {
        return profession;
    }
    public void setProfession(String p)
    {
        profession = p;
    }
    public String getIdcard()
    {
        return idcard;
    }
    public void setIdcard(String i)
    {
        idcard  = i;
    }


}
