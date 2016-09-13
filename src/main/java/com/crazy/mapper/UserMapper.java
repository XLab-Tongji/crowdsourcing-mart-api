package com.crazy.mapper;

import com.crazy.model.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by SHIKUN on 2016/9/13.
 */
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO USERS(user_name,phone,email,password,address,qq) VALUES (#{user_name},#{phone},#{email},#{password}," +
            "#{address},#{qq})")
    public int addUser(@Param("user_name") String user_name, @Param("phone") String phone, @Param("email") String email, @Param("password") String password,
                       @Param("address") String address, @Param("qq") String qq);

    @Select("SELECT * FROM USERS")
    public List<Users> searchAll();



}
