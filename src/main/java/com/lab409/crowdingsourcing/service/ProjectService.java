package com.lab409.crowdingsourcing.service;

import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import com.lab409.crowdingsourcing.entity.DevInfo;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;

import java.util.List;

/**
 * Created by SHIKUN on 2016/10/29.
 */
public interface ProjectService {

    //获取所有用户创建需求列表
    ResJsonTemplate getAllproject();

    //用户创建需求
    ResJsonTemplate addProject(double cost, int delivery_day, int warrenty_cycle, String address, String description,
                               String username, String project_type, String project_name);

    //用户报名接口
    ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo);

    //用户取消报名接口
    ResJsonTemplate deleteEnrollInfo(String username, Long enroll_project_id);

    //根据项目ID获取项目报名人数
    ResJsonTemplate getEnrollCountByProjectId(Long id);

    //根据开发者姓名获取已报名的项目列表
    ResJsonTemplate getEnrollListByDevUsername(String username);

    //根据用户姓名获取已创建的项目列表
    ResJsonTemplate getProjectListbyusername(String username);

    //根据用户名和id得到用户创建的需求详情
    ResJsonTemplate getProjectDetailbyUsernameId(String username, Long id);

    //根据项目ID获取项目详情
    ResJsonTemplate getProjectDetailByProjectId(Long id);

    //需求方确认开发者报名
    ResJsonTemplate confirmDevelop(DevInfo devInfo);

    //需求方确认开发者报名(多个确认)
    ResJsonTemplate confirmDevelopMember(List<DevInfo> devInfos);

    //根据项目id获取开发者数量
    ResJsonTemplate getDeveloperCountbyProjectId(Long id);

    //根据开发者用户名获取开发者开发项目列表
    ResJsonTemplate getDevProjectListbyDevelopUsername(String username);

    //根据项目id获取报名者用户名列表
    ResJsonTemplate getDevelopUsernameListByProjectId(Long id);

    //根据项目id获取报名者详情接口
    ResJsonTemplate getDevelopDetailByProjectId(Long id);

    //根据页码来获取项目列表 start是页码，size是每页显示的数目
    ResJsonTemplate getProjectListbyPageNumber(int start, int size);

    //获取开发者的项目列表（包括申请中，开发中，已完成的项目）
    ResJsonTemplate getProjectList(String username);

    void deleteProject(Long id);

}
