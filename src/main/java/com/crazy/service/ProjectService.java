package com.crazy.service;

import com.crazy.entity.DevEnrollInfo;
import com.crazy.entity.DevInfo;
import com.crazy.entity.Project;
import com.crazy.util.ResJsonTemplate;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by SHIKUN on 2016/10/29.
 */
public interface ProjectService {

    //获取所有用户创建需求列表
    public ResJsonTemplate getAllproject();

    //用户创建需求
    public ResJsonTemplate addProject(Project project);

    //用户报名接口
    public ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo);

    //用户取消报名接口
    public ResJsonTemplate deleteEnrollInfo(String username, Long enroll_project_id);

    //根据项目ID获取项目报名人数
    public ResJsonTemplate getEnrollCountByProjectId(Long id);

    //根据开发者姓名获取已报名的项目列表
    public ResJsonTemplate getEnrollListByDevUsername(String username);

    //根据用户姓名获取已创建的项目列表
    public ResJsonTemplate getProjectListbyusername(String username);

    //根据用户名和id得到用户创建的需求详情
    public ResJsonTemplate getProjectDetailbyUsernameId(String username, Long id);

    //根据项目ID获取项目详情
    public ResJsonTemplate getProjectDetailByProjectId(Long id);

    //需求方确认开发者报名
    public ResJsonTemplate confirmDevelop(DevInfo devInfo);

    //需求方确认开发者报名(多个确认)
    public ResJsonTemplate confirmDevelopMember(List<DevInfo> devInfos);

    //根据项目id获取开发者数量
    public ResJsonTemplate getDeveloperCountbyProjectId(Long id);

    //根据开发者用户名获取开发者开发项目列表
    public ResJsonTemplate getDevProjectListbyDevelopUsername(String username);

    //根据项目id获取报名者用户名列表
    public ResJsonTemplate getDevelopUsernameListByProjectId(Long id);

    //根据项目id获取报名者详情接口
    public ResJsonTemplate getDevelopDetailByProjectId(Long id);

    //根据页码来获取项目列表 start是页码，size是每页显示的数目
    public ResJsonTemplate getProjectListbyPageNumber(int start, int size);



}
