package com.crazy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * Created by SHIKUN on 2016/11/2.
 */
@Mapper
public interface AttachMapper {

    @Insert("INSERT INTO ATTACH (attach_url,attach_name,size,attach_type,create_time,update_time,meta_data,username,is_del) VALUES " +
            "(#{attach_url},#{attach_name},#{size},#{attach_type},NOW(),NOW(),#{meta_data},#{username},#{is_del})")
    int insertFile(@Param("attach_url") String attach_url, @Param("attach_name") String attach_name,@Param("size") Integer size, @Param("attach_type") String attach_type,
                          @Param("meta_data") String meta_data, @Param("username") String username, @Param("is_del") boolean is_del);


}
