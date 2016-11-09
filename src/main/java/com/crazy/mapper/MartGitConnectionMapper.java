package com.crazy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Ni on 2016/11/9.
 */

@Mapper
public interface MartGitConnectionMapper {
    @Insert("INSERT INTO MART_GIT_CONNECTION (mart_account_id,git_account_id) VALUES" +
            " (#{mart_account_id},#{git_account_id})")
    int addConnection(@Param("mart_account_id") int martId, @Param("git_account_id") int name);
}
