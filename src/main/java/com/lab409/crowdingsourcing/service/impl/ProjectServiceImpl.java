package com.lab409.crowdingsourcing.service.impl;

import com.lab409.crowdingsourcing.dto.SimpleProjectDto;
import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import com.lab409.crowdingsourcing.entity.DevInfo;
import com.lab409.crowdingsourcing.entity.Project;
import com.lab409.crowdingsourcing.mapper.ProjectMapper;
import com.lab409.crowdingsourcing.repository.DevEnrollInfoRepository;
import com.lab409.crowdingsourcing.repository.DevInfoRepository;
import com.lab409.crowdingsourcing.repository.ProjectRepository;
import com.lab409.crowdingsourcing.service.FileService;
//import com.lab409.crowdingsourcing.service.ProjectInfo;
import com.lab409.crowdingsourcing.service.ProjectService;
import com.lab409.crowdingsourcing.util.Paging;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目相关接口实现
 * Created by SHIKUN on 2016/10/29.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DevEnrollInfoRepository devEnrollInfoRepository;
    @Autowired
    private DevInfoRepository devInfoRepository;

    @Autowired
    private Paging paging;

    @Autowired
    private FileService fileService;

    //List<ProjectInfo> projects = new ArrayList<>();

    @Override
    public void deleteProject(Long id){
        projectRepository.delete(id);
    }

    @Override
    public ResJsonTemplate getAllproject() {

        try {
            //       return new ResJsonTemplate("200", projectMapper.searchProjectall());
            return new ResJsonTemplate("200", projectRepository.findAll());
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate addProject(double cost, int delivery_day, int warrenty_cycle, String address, String description,
                                      String username, String project_type, String project_name) {


//        ResJsonTemplate response1=fileService.insertFile(file, "src/main/webapp", username);

        //       int result=projectMapper.addProject(cost, delivery_day, warrenty_cycle, address, description, username, project_type, project_name);
        Project project = projectRepository.save(new Project(cost, delivery_day, warrenty_cycle, address, description, username, project_type, project_name));
//        if((response1.getStatus()=="200")&&(result==1)){
        return new ResJsonTemplate("200", project.getprojectId());
//        }else {
//            return new ResJsonTemplate("500", "创建失败");
//        }
    }

    @Override
    public ResJsonTemplate addEnrollInfo(DevEnrollInfo devEnrollInfo) {
        try {
            return new ResJsonTemplate("200", devEnrollInfoRepository.save(devEnrollInfo));
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("error");
            return new ResJsonTemplate("500", ex);
        }
    }

    @Override
    public ResJsonTemplate deleteEnrollInfo(String username, Long enroll_project_id) {
        try {
            //          return new ResJsonTemplate("200", projectMapper.deleteEnrollInfo(enroll_project_id,username));
            List<DevEnrollInfo> list = devEnrollInfoRepository.findByProjectIdAndUsername(enroll_project_id, username);
            for (int i = 0; i < list.size(); i++) {
                devEnrollInfoRepository.delete(list.get(i));
            }
            return new ResJsonTemplate("200", "delete successfully");
        } catch (Exception ex) {
            return new ResJsonTemplate("500", ex);
        }
    }

    @Override
    public ResJsonTemplate getEnrollCountByProjectId(Long id) {
        try {
            //   return new ResJsonTemplate("200", projectMapper.getProjectCount(id));
            return new ResJsonTemplate("200", devEnrollInfoRepository.findByProjectId(id).size());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResJsonTemplate("500", "未查询到项目报名");
        }
    }

    @Override
    public ResJsonTemplate getEnrollListByDevUsername(String username) {
        try {
            return new ResJsonTemplate("200", projectRepository.searchEnrollProjectByUsername(username));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResJsonTemplate("500", "未查询到项目");
        }
    }

    @Override
    public ResJsonTemplate getProjectListbyusername(String username) {
        try {
            List<Project> projects = projectRepository.findByUsername(username);
            List<SimpleProjectDto> simpleProjectDtos = new LinkedList<>();
            for (Project p: projects) {
                simpleProjectDtos.add(new SimpleProjectDto(p.getprojectId(),p.getProjectName(),p.getProjectType(),p.getProjectState()));
            }
            //      return new ResJsonTemplate("200", projectMapper.searchProjectbycreatUser(username));
            return new ResJsonTemplate("200", simpleProjectDtos);
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getProjectDetailbyUsernameId(String username, Long id) {
        try {

            //     return new ResJsonTemplate("200", projectMapper.searchProjectbyId(id, username));
            return new ResJsonTemplate("200", projectRepository.findByProjectIdAndUsername(id, username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getProjectDetailByProjectId(Long id) {
        try {
            //       return new ResJsonTemplate("200", projectMapper.searchProjectonlyId(id));
            return new ResJsonTemplate("200", projectRepository.findByProjectId(id));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }

    }

    @Override
    public ResJsonTemplate confirmDevelop(DevInfo devInfo) {
        try {
            /*
            return new ResJsonTemplate("200", projectMapper.insertDevelopingInfo(devInfo.getUsername(),
                    devInfo.getProjectId()));*/
            return new ResJsonTemplate("200", devInfoRepository.save(devInfo));
        } catch (Exception e) {

            System.out.println(e);
            return new ResJsonTemplate("500", e);
        }
    }


    @Override
    public ResJsonTemplate confirmDevelopMember(List<DevInfo> devInfos) {
        try {
            for (int i = 0; i < devInfos.size(); i++) {
                devInfoRepository.save(devInfos.get(i));
                //         projectMapper.insertDevelopingInfo(devInfos.get(i).getUsername(), devInfos.get(i).getProjectId());

            }
            return new ResJsonTemplate("200", "ok");
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "插入失败");
        }
    }

    @Override
    public ResJsonTemplate getDeveloperCountbyProjectId(Long id) {
        try {
            //     return new ResJsonTemplate("200", projectMapper.getDevelopProjectCount(id));
            return new ResJsonTemplate("200", devInfoRepository.findByProjectId(id).size());
        } catch (Exception e) {
            System.out.println(e);
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getDevProjectListbyDevelopUsername(String username) {
        try {
            return new ResJsonTemplate("200", projectRepository.searchDevelopingProjectbyUsername(username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getDevelopUsernameListByProjectId(Long id) {
        try {
            //       return new ResJsonTemplate("200", projectMapper.searchEnrollmemberbyProjectId(id));
            List<DevEnrollInfo> listD = devEnrollInfoRepository.findByProjectId(id);
            List<String> listS = new LinkedList<String>();
            for (int i = 0; i < listD.size(); i++) {
                listS.add(listD.get(i).getUsername());
            }
            return new ResJsonTemplate("200", listS);
        } catch (Exception e) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getDevelopDetailByProjectId(Long id) {

        try {
            return new ResJsonTemplate("200", projectRepository.searchDeveloperEnrollInfo(id));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @Override
    public ResJsonTemplate getProjectListbyPageNumber(int start, int size) {


        List result = new LinkedList();

        Map<String, Integer> pageinfo = new HashMap<>();


        //  int totalPage=paging.getTotalPage(projectMapper.getProjectCountPage(),size);
        int totalPage = paging.getTotalPage((int) projectRepository.count(), size);
        int startPage = paging.convertStartPage(start, size);

        pageinfo.put("totalPage", totalPage);
        pageinfo.put("currentPage", start);

        result.add(pageinfo);
        result.add(projectMapper.getAllProjectByPage(startPage, size));

        return new ResJsonTemplate("200", result);
    }

    @Override
    public ResJsonTemplate getProjectList(String username) {
        /*
        List<Project> enrollProjects = new ArrayList<>();
        List<Project> developingProjects = new ArrayList<>();
        enrollProjects = projectRepository.searchEnrollProjectByUsername(username);
        developingProjects = projectRepository.searchDevelopingProjectbyUsername(username);
        convertToProjectInfo(enrollProjects);
        convertToProjectInfo(developingProjects);
        return new ResJsonTemplate("200", projects);
        */

//        List<Project> result = projectRepository.findByUsername(username);
        List<SimpleProjectDto> result = new LinkedList<>();
        List<DevEnrollInfo> devEnrollInfos = devEnrollInfoRepository.findByUsername(username);
        for(int i = 0;i<devEnrollInfos.size();i++)
        {
            Project temp = projectRepository.findByProjectId(devEnrollInfos.get(i).getProjectId());
            if(temp == null){
                return new ResJsonTemplate("200",result);
            }
            if(!result.contains(temp))
            {
                result.add(new SimpleProjectDto(temp.getprojectId(),temp.getProjectName(),temp.getProjectType(),temp.getProjectState()));
            }
        }
        return new ResJsonTemplate("200",result);
    }

    /*public void convertToProjectInfo(List<Project> projectList) {
        for (int i = 0; i < projectList.size(); i++) {
            Long projectId = projectList.get(i).getprojectId();
            String projectName = projectList.get(i).getProjectName();
            String projectType = projectList.get(i).getProjectType();
            int projectState = projectList.get(i).getProjectState();
            ProjectInfo projectInfo = new ProjectInfo(projectId, projectName, projectType, projectState);
            projects.add(projectInfo);
        }
    }*/
}
