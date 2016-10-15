package com.crazy.mapper;

import com.crazy.model.AccountLogin;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SHIKUN on 2016/9/30.
 */
@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO ACCOUNT (username,name,icon,password,mobile,create_time,update_time,email,ext_params) VALUES" +
            " (#{username},#{name},#{icon},#{password},#{mobile},#{create_time},#{update_time},#{email},#{ext_params})")
    public int addAcount(@Param("username") String username, @Param("name") String name, @Param("icon") String icon,
                         @Param("password") String password, @Param("mobile") String mobile,
                         @Param("create_time") Date create_time, @Param("update_time") Date update_time,
                         @Param("email") String email, @Param("ext_params") String ext_params);

    @Select("SELECT username FROM ACCOUNT")
    public List<String> getAllUsername();

    @Select("SELECT id FROM ACCOUNT WHERE username=#{username}")
    public Long getUserId(@Param("username") String username);


    @Select("SELECT username,password FROM ACCOUNT")
    public List<String> getNameAndPassword();

    @Select("SELECT username,password FROM ACCOUNT WHERE username=#{username}")
    public Map<String, String> getCheckInfo(@Param("username") String username);

    @Insert("INSERT INTO ACCOUNT_LOGIN_LOG (ip,token,create_time,expire_time,account_id,plat,username) VALUES " +
            "(#{ip},#{token},#{create_time},#{expire_time},#{account_id},#{plat},#{username})")
    public int addLoginLog(@Param("ip") String ip, @Param("token") String token, @Param("create_time") Date create_time,
                           @Param("expire_time") Date expire_time, @Param("account_id") Long account_id,
                           @Param("plat") String plat, @Param("username") String username);

    @Select("SELECT id,expire_time,account_id FROM ACCOUNT_LOGIN_LOG WHERE token=#{token}")
    public AccountLogin getTokenInfo(@Param("token") String token);

    @Update("UPDATE ACCOUNT_LOGIN_LOG SET token=#{token}, create_time=#{create_time},expire_time=#{expire_time} WHERE id=#{id}")
    public int updateToken(@Param("token") String token, @Param("create_time") Date create_time, @Param("expire_time") Date expire_time,
                           @Param("id") Long id);

    @Insert("INSERT DEVELOPER (username,account_id,dev_domain) VALUES (" +
            "#{username},#{account_id},#{dev_domain})")
    public int addDevInfo(@Param("username") String username, @Param("account_id") Long account_id, @Param("dev_domain") String dev_domain);

    @Update("UPDATE DEVELOPER SET dev_domain=#{dev_domain} WHERE id=#{id}")
    public int updateDevInfo(@Param("dev_domain") String dev_domain);

    @Update("UPDATE DEVELOPER SET dev_project=#{dev_project} WHERE id=#{id}")
    public int updateDevproject(@Param("dev_project") String dev_project);

    @Update("UPDATE DEVELOPER SET project_enroll=#{project_enroll} WHERE username=#{username}")
    public int updateProjectEnroll(@Param("project_enroll") String project_enroll, @Param("username") String username);

    @Update("UPDATE ACCOUNT SET dev_id=#{dev_id} WHERE username=#{username}")
    public int changeDevStatus(@Param("dev_id") Long dev_id, @Param("username") String username);

    @Select("SELECT id FROM DEVELOPER WHERE username=#{username}")
    public Long getDevloperId(@Param("username") String username);







}

