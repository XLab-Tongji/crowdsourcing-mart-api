package com.crazy.mapper;

import com.crazy.model.Item;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SHIKUN on 2016/9/11.
 */

@Mapper
public interface ItemMapper  {

    @Select("SELECT i FROM Item i WHERE i.checked=true ")
    List<Item> findChecked();

    @Select("SELECT * FROM Item")
    List<Item> findall();

    @Insert("INSERT INTO Item (checked,description) VALUES(#{checked},#{description})")
    int addItem(@Param("checked") boolean checked, @Param("description") String description);

    @Delete("DELETE FROM Item WHERE id=#{id}")
    int deleteItem(@Param("id") int id);

    @Update("UPDATE Item SET checked=#{checked} WHERE id=#{id}")
    int update(@Param("id") int id, @Param("checked") boolean checked);

    }



