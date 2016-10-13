package com.crazy.mapper;

import com.crazy.model.Project;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO PROJECT (cost,delivery_cycle,warranty_cycle,address,description,project_user_name,project_type,create_date) VALUES (#{cost},#{delivery_cycle},#{warranty_cycle},#{address},#{description},#{project_user_name},#{project_type},#{create_date})")
    public int addProject(@Param("cost") double cost, @Param("delivery_cycle") Integer devlivery_cycle, @Param("warranty_cycle") Integer warranty_cycle,
                          @Param("address") String address, @Param("description") String description, @Param("project_user_name") String user_name, @Param("project_type") String project_type, @Param("create_date")Date create_time);

    @Select("SELECT * FROM PROJECT")
    public List<Project> searchProjectall();

    @Update("UPDATE PROJECT SET dev_user_name=#{username} WHERE id=#{id}")
    public int claimProject(@Param("username") String username, @Param("id") Long id);






}
