package com.crazy.mapper;

import com.crazy.model.Project;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.util.Date;
import java.util.List;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO PROJECT (cost,delivery_cycle,warranty_cycle,address,description,project_user_name,project_type,create_date,project_name) " +
            "VALUES (#{cost},#{delivery_cycle},#{warranty_cycle},#{address},#{description},#{project_user_name},#{project_type},NOW(),#{project_name})")
    public int addProject(@Param("cost") double cost, @Param("delivery_cycle") Integer devlivery_cycle, @Param("warranty_cycle") Integer warranty_cycle,
                          @Param("address") String address, @Param("description") String description, @Param("project_user_name") String user_name,
                          @Param("project_type") String project_type, @Param("project_name") String project_name);

    @Select("SELECT * FROM PROJECT")
    public List<Project> searchProjectall();

    @Update("UPDATE PROJECT SET enroll_dev_list=#{enroll_dev_list) WHERE id=#{id}")
    public int updateEnrollList(@Param("enroll_dev_list") String enroll_dev_list, @Param("id") Long id);

    @Update("UPDATE PROJECT SET dev_username=#{dev_username} WHERE id=#{id}")
    public int updateDevusername(@Param("dev_username") String username, @Param("id") Long id);

    @Insert("INSERT DEV_PRO_INFO (dev_username,enroll_project_id,enroll_date) VALUES (#{dev_username},#{enroll_project_id},NOW())")
    public int insertDevProInfo(@Param("dev_username") String dev_username, @Param("enroll_project_id") Long enroll_project_id);

    @Select("SELECT * FROM PROJECT WHERE project_user_name=#{project_user_name}")
    public List<Project> searchProjectbycreatUser(@Param("project_user_name") String project_user_name);

    @Select("SELECT * FROM PROJECT WHERE id=#{id} AND project_user_name=#{project_user_name}")
    public List<Project> searchProjectbyId(@Param("id") Long id, @Param("project_user_name") String project_user_name);

    @Select("SELECT * FROM PROJECT WHERE id=#{id}")
    public List<Project> searchProjectonlyId(@Param("id") Long id);

    @Delete("DELETE FROM DEV_PRO_INFO WHERE enroll_project_id=#{enroll_project_id} AND dev_username=#{dev_username}")
    public int deleteEnrollInfo(@Param("enroll_project_id") Long enroll_project_id, @Param("dev_username") String dev_username);

    @Select("SELECT COUNT(enroll_project_id) AS count FROM DEV_PRO_INFO WHERE enroll_project_id=#{enroll_project_id}")
    public int getProjectCount(@Param("enroll_project_id") Long enroll_project_id);

    @Select("SELECT enroll_project_id FROM DEV_PRO_INFO WHERE dev_username=#{username}")
    public List<Integer> searchProjectIdbyUsername(@Param("username") String username);

    @Select("SELECT * FROM DEV_PRO_INFO a LEFT JOIN PROJECT b ON a.enroll_project_id=b.id WHERE a.dev_username=#{username}")
    public List<Project> searchProjectInfobyUsername(@Param("username") String username);

    @Insert("INSERT DEVELOPING_INFO (username,project_id,create_date) VALUES (#{username},#{project_id},NOW())")
    public int insertDevelopingInfo(@Param("username") String username, @Param("project_id") Long project_id);





}
