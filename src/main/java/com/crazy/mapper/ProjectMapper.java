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
    @Insert("INSERT INTO PROJECT (cost,delivery_cycle,warranty_cycle,address,description,project_user_name,project_type,project_name) " +
            "VALUES (#{cost},#{delivery_cycle},#{warranty_cycle},#{address},#{description},#{project_user_name},#{project_type},#{create_date},#{project_name})")
    public int addProject(@Param("cost") double cost, @Param("delivery_cycle") Integer devlivery_cycle, @Param("warranty_cycle") Integer warranty_cycle,
                          @Param("address") String address, @Param("description") String description, @Param("project_user_name") String user_name,
                          @Param("project_type") String project_type, @Param("create_date") Date create_time, @Param("project_name") String project_name);

    @Select("SELECT * FROM PROJECT")
    public List<Project> searchProjectall();

    @Update("UPDATE PROJECT SET enroll_dev_list=#{enroll_dev_list) WHERE id=#{id}")
    public int updateEnrollList(@Param("enroll_dev_list") String enroll_dev_list, @Param("id") Long id);

    @Update("UPDATE PROJECT SET dev_username=#{dev_username} WHERE id=#{id}")
    public int updateDevusername(@Param("dev_username") String username, @Param("id") Long id);

    @Insert("INSERT DEV_PRO_INFO (dev_username,enroll_project_id,enroll_date) VALUES (#{dev_username},#{enroll_project_id},NOW())")
    public int insertDevProInfo(@Param("dev_username") String dev_username, @Param("enroll_project_id") Long enroll_project_id);



}
