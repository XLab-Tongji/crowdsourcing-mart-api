package com.crazy.mapper;

import com.crazy.model.Developer;
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
    @Insert("INSERT INTO PROJECT (cost,delivery_cycle,warranty_cycle,address,description,username,project_type,create_date,project_name,enroll_stop_time,update_date) " +
            "VALUES (#{cost},#{delivery_cycle},#{warranty_cycle},#{address},#{description},#{username},#{project_type},NOW(),#{project_name},NOW(),NOW())")
    public int addProject(@Param("cost") double cost, @Param("delivery_cycle") Integer devlivery_cycle, @Param("warranty_cycle") Integer warranty_cycle,
                          @Param("address") String address, @Param("description") String description, @Param("username") String username,
                          @Param("project_type") String project_type, @Param("project_name") String project_name);

    @Select("SELECT * FROM PROJECT")
    public List<Project> searchProjectall();


    @Insert("INSERT DEV_ENROLL_INFO (username,project_id,enroll_date) VALUES (#{username},#{project_id},NOW())")
    public int insertDevProInfo(@Param("username") String username, @Param("project_id") Long project_id);

    @Select("SELECT * FROM PROJECT WHERE username=#{username}")
    public List<Project> searchProjectbycreatUser(@Param("username") String username);

    @Select("SELECT * FROM PROJECT WHERE project_id=#{project_id} AND username=#{username}")
    public List<Project> searchProjectbyId(@Param("project_id") Long project_id, @Param("username") String username);

    @Select("SELECT * FROM PROJECT WHERE project_id=#{project_id}")
    public List<Project> searchProjectonlyId(@Param("project_id") Long project_id);

    @Delete("DELETE FROM DEV_ENROLL_INFO WHERE project_id=#{project_id} AND username=#{username}")
    public int deleteEnrollInfo(@Param("project_id") Long project_id, @Param("username") String username);

    @Select("SELECT COUNT(project_id) AS count FROM DEV_ENROLL_INFO WHERE project_id=#{project_id}")
    public int getProjectCount(@Param("project_id") Long project_id);

    @Select("SELECT project_id FROM DEV_ENROLL_INFO WHERE username=#{username}")
    public List<Integer> searchProjectIdbyUsername(@Param("username") String username);

    @Select("SELECT * FROM DEV_ENROLL_INFO a LEFT JOIN PROJECT b ON a.project_id=b.project_id WHERE a.username=#{username}")
    public List<Project> searchProjectInfobyUsername(@Param("username") String username);

    @Insert("INSERT DEVELOPING_INFO (username,project_id,confirm_date) VALUES (#{username},#{project_id},NOW())")
    public int insertDevelopingInfo(@Param("username") String username, @Param("project_id") Long project_id);

    @Select("SELECT COUNT(project_id) AS countlist FROM DEVELOPING_INFO WHERE project_id=#{project_id}")
    public int getDevelopProjectCount(@Param("project_id") Long project_id);

    @Select("SELECT * FROM DEVELOPING_INFO a LEFT JOIN PROJECT b ON a.project_id=b.project_id WHERE a.username=#{username}")
    public List<Project> searchDevelopingProjectbyUsername(@Param("username") String username);

    @Select("SELECT username FROM DEV_ENROLL_INFO WHERE project_id=#{project_id}")
    public List<String> searchEnrollmemberbyProjectId(@Param("project_id") Long project_id);

    @Select("SELECT * FROM DEV_ENROLL_INFO a LEFT JOIN DEVELOPER b ON a.username=b.username WHERE project_id=#{project_id}")
    public List<Developer> searchDeveloperEnrollInfo(@Param("project_id") Long project_id);








}
