package com.crazy.entity;

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
    private String requirement_name;
    private String requirement_type;
    private Date start_time;
    private Date end_time;
    private int need_manager;
    private String requirement_detail;
    private byte[] file;
    public void setId(Long i)
    {
        id = i;
    }
    public Long getId()
    {
        return id;
    }
    public void setRequirement_name(String r)
    {
        requirement_name = r;
    }
    public String getRequirement_name()
    {
        return requirement_name;
    }
    public void setRequirement_type(String t)
    {
        requirement_type = t;
    }
    public String getRequirement_type()
    {
        return requirement_type;
    }
    public void setStart_time(Date s)
    {
        start_time = s;
    }
    public Date getStart_time()
    {
        return start_time;
    }
    public void setEnd_time(Date e)
    {
        end_time  = e;
    }
    public Date getEnd_time()
    {
        return end_time;
    }
    public void setNeed_manager(int n)
    {
        need_manager = n;
    }
    public int getNeed_manager()
    {
        return need_manager;
    }
    public void setRequirement_detail(String d)
    {
        requirement_detail = d;
    }
    public String getRequirement_detail()
    {
        return requirement_detail;
    }
    public void setFile(byte[] f)
    {
        file = f;
    }
    public byte[] getFile()
    {
        return file;
    }

}
