package com.crazy.mapper;

import com.crazy.model.Project;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@Mapper
public interface ProjectMapper {
    @Insert("INSERT INTO PROJECT (cost,delivery_cycle,warranty_cycle,address,description) VALUES (#{cost},#{devlivery_cycle},#{warranty_cycle},#{address},#{description})")
    public int addProject(@Param("cost") double cost, @Param("devlivery_cycle") Integer devlivery_cycle, @Param("warranty_cycle") Integer warranty_cycle,
                          @Param("address") String address,@Param("description") String description);

    @Select("SELECT * FROM PROJECT")
    public Project searchProjectall();




}
