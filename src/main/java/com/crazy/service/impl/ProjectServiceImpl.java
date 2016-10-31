package com.crazy.service.impl;

import com.crazy.entity.DevEnrollInfo;
import com.crazy.entity.DevInfo;
import com.crazy.entity.Project;
import com.crazy.mapper.ProjectMapper;
import com.crazy.service.ProjectService;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**项目相关接口实现
 * Created by SHIKUN on 2016/10/29.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;




    @Override
    public ResJsonTemplate getAllproject() {
        try {
            return new ResJsonTemplate("200", projectMapper.searchProjectall());
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate addProject(Project project) {
        try {
            return new ResJsonTemplate("200", projectMapper.addProject(project.getCost(), project.getDelivery_cycle(),
                    project.getWarranty_cycle(), project.getAddress(), project.getDescription(), project.getUsername(),
                    project.getProject_type(), project.getProject_name()));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "项目创建失败");
        }
    }

    @Override
    public ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo) {
        try {

            return new ResJsonTemplate("200", projectMapper.insertDevProInfo(
                    devEnrollInfo.getUsername(), devEnrollInfo.getProject_id()));

        } catch (Exception ex) {
            System.out.println(ex);
            return new ResJsonTemplate("500", ex);

        }
    }

    @Override
    public ResJsonTemplate deleteEnrollInfo(String username, Long enroll_project_id) {
        try {
            return new ResJsonTemplate("200", projectMapper.deleteEnrollInfo(enroll_project_id,username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", ex);
        }
    }

    @Override
    public ResJsonTemplate getEnrollCountByProjectId(Long id) {
        try {
            return new ResJsonTemplate("200", projectMapper.getProjectCount(id));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResJsonTemplate("500", "未查询到项目报名");
        }
    }

    @Override
    public ResJsonTemplate getEnrollListByDevUsername(String username) {
        try {
            return new ResJsonTemplate("200", projectMapper.searchProjectInfobyUsername(username));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResJsonTemplate("500", "未查询到项目");
        }
    }

    @Override
    public ResJsonTemplate getProjectListbyusername(String username) {
        try {
            return new ResJsonTemplate("200", projectMapper.searchProjectbycreatUser(username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getProjectDetailbyUsernameId(String username, Long id) {
        try {
            return new ResJsonTemplate("200", projectMapper.searchProjectbyId(id, username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getProjectDetailByProjectId(Long id) {
        try {
            return new ResJsonTemplate("200", projectMapper.searchProjectonlyId(id));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }

    }

    @Override
    public ResJsonTemplate confirmDevelop(DevInfo devInfo) {
        try {
            return new ResJsonTemplate("200", projectMapper.insertDevelopingInfo(devInfo.getUsername(),
                    devInfo.getProject_id()));
        } catch (Exception e) {

            System.out.println(e);
            return new ResJsonTemplate("500", e);
        }
    }


    @Override
    public ResJsonTemplate confirmDevelopMember(List<DevInfo> devInfos) {
        try {
            for (int i = 0; i < devInfos.size(); i++) {
                projectMapper.insertDevelopingInfo(devInfos.get(i).getUsername(), devInfos.get(i).getProject_id());

            }
            return new ResJsonTemplate("200", "ok");
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "插入失败");
        }
    }

    @Override
    public ResJsonTemplate getDeveloperCountbyProjectId(Long id) {
        try {
            return new ResJsonTemplate("200", projectMapper.getDevelopProjectCount(id));
        } catch (Exception e) {
            System.out.println(e);
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getDevProjectListbyDevelopUsername(String username) {
        try {
            return new ResJsonTemplate("200", projectMapper.searchDevelopingProjectbyUsername(username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getDevelopUsernameListByProjectId(Long id) {
        try {
            return new ResJsonTemplate("200", projectMapper.searchEnrollmemberbyProjectId(id));
        } catch (Exception e) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getDevelopDetailByProjectId(Long id) {

        try {
            return new ResJsonTemplate("200", projectMapper.searchDeveloperEnrollInfo(id));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getProjectListbyPageNumber(int pageNumber, int displayNumber) {


        try {
            //获取项目总数
            Long dataAllcount = Long.valueOf(projectMapper.searchProjectall().size());
            System.out.println(dataAllcount);







            return new ResJsonTemplate("200", projectMapper.getAllProjectByPage(pageNumber, displayNumber));
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResJsonTemplate("500", "获取失败");

        }
    }
}
