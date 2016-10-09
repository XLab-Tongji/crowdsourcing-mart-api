package com.crazy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.access.method.P;

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
    public int addAcount(@Param("username") String username, @Param("name") String name, @Param("icon") String icon,@Param("password") String password,
                         @Param("mobile") String mobile, @Param("create_time") Date create_time, @Param("update_time") Date update_time,
                         @Param("email") String email, @Param("ext_params") String ext_params);

    @Select("SELECT username FROM ACCOUNT")
    public List<String> getAllUsername();

    @Select("SELECT id FROM ACCOUNT WHERE username=#{username}")
    public Long getUserId(@Param("username") String username);


    @Select("SELECT username,password FROM ACCOUNT")
    public List<String> getNameAndPassword();

    @Select("SELECT username,password FROM ACCOUNT WHERE username=#{username}")
    public Map<String,String> getCheckInfo(@Param("username") String username);

    @Insert("INSERT INTO ACCOUNT_LOGIN_LOG (ip,token,create_time,expire_time,account_id,plat) VALUES " +
            "(#{ip},#{token},#{create_time},#{expire_time},#{account_id},#{plat})")
    public int addLoginLog(@Param("ip") String ip, @Param("token") String token, @Param("create_time") Date create_time,
                           @Param("expire_time") Date expire_time, @Param("account_id") Long account_id, @Param("plat") String plat);












}
