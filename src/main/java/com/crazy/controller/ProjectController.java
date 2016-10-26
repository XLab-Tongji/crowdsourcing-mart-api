package com.crazy.controller;

import com.crazy.mapper.AccountMapper;
import com.crazy.mapper.ProjectMapper;
import com.crazy.model.DevEnrollInfo;
import com.crazy.model.DevInfo;
import com.crazy.model.Project;
import com.crazy.util.ConvertJson;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@RestController
@RequestMapping(value = "api/project")
@Transactional
public class ProjectController {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private ConvertJson convertJson;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate  addProject(@RequestBody Project project) {
        return new ResJsonTemplate("200", projectMapper.addProject(project.getCost(), project.getDelivery_cycle(),
                project.getWarranty_cycle(), project.getAddress(), project.getDescription(), project.getUsername(),
                project.getProject_type(), project.getProject_name()));

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate  searchproject() {
        return new ResJsonTemplate("200",projectMapper.searchProjectall());
    }


    @RequestMapping(value = "develop/enroll", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate addDevenrollInfo(@RequestBody DevEnrollInfo devEnrollInfo) {

        try {

            return new ResJsonTemplate("200", projectMapper.insertDevProInfo(
                    devEnrollInfo.getUsername(), devEnrollInfo.getProject_id()));
        } catch (Exception ex) {
            System.out.println(ex);
            return new ResJsonTemplate("500", ex);
        }


    }

    @RequestMapping(value = "develop/enroll/cancel", method = RequestMethod.DELETE)
    public @ResponseBody ResJsonTemplate deleteEnrollInfo(@RequestParam(value = "dev_username") String dev_username,
                                     @RequestParam(value = "enroll_project_id") Long enroll_project_id) {
        try {
            return new ResJsonTemplate("200", projectMapper.deleteEnrollInfo(enroll_project_id, dev_username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", ex);
        }
    }

    @RequestMapping(value = "develop/enroll/count/{id}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getProjectEnrollcount(@PathVariable Long id){
        try {
            return new ResJsonTemplate("200", projectMapper.getProjectCount(id));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResJsonTemplate("500","未查询到项目报名");
        }
    }

    @RequestMapping(value = "develop/enroll/list/{username}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getEnrolledProjectbyUsername(@PathVariable String username){
        try {
            return new ResJsonTemplate("200", projectMapper.searchProjectInfobyUsername(username));
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResJsonTemplate("500", "未查询到项目");
        }

    }



    @RequestMapping(value ="/list/{user}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getProjectListper(@PathVariable String user){
        return new ResJsonTemplate("200", projectMapper.searchProjectbycreatUser(user));
    }

    @RequestMapping(value="/list/{user}/{id}",method = RequestMethod.GET)
    public @ResponseBody  ResJsonTemplate getProjectListbyId(@PathVariable String user,@PathVariable Long id){
        return new ResJsonTemplate("200", projectMapper.searchProjectbyId(id, user));
    }


    @RequestMapping(value = "/list/id/{id}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getProjectListOnlyId(@PathVariable Long id) {
        return new ResJsonTemplate("200", projectMapper.searchProjectonlyId(id));
    }

    @RequestMapping(value = "develop/confirm", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate confirmProject(@RequestBody DevInfo devInfo) {
        try {
            return new ResJsonTemplate("200", projectMapper.insertDevelopingInfo(devInfo.getUsername(),
                    devInfo.getProject_id()));
        } catch (Exception e) {

            System.out.println(e);
            return new ResJsonTemplate("500", e);
        }
    }


    @RequestMapping(value = "develop/project/{id}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getDevelopmember(@PathVariable Long id){
        try {
            return new ResJsonTemplate("200", projectMapper.getDevelopProjectCount(id));
        } catch (Exception e) {
            System.out.println(e);
            return new ResJsonTemplate("500", "查询失败");
        }
    }


    @RequestMapping(value = "develop/confirm/member", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate confirmProjectmember(@RequestBody List<DevInfo> devInfos) {

        try {
            for (int i = 0; i < devInfos.size(); i++) {
                projectMapper.insertDevelopingInfo(devInfos.get(i).getUsername(), devInfos.get(i).getProject_id());

            }
            return new  ResJsonTemplate("200", "ok");
        } catch (Exception ex) {
            return new ResJsonTemplate("500","插入失败");
        }
    }

    @RequestMapping(value = "develop/list/{username}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getDevelopingProjectInfo(@PathVariable String username){
        try {
            return new ResJsonTemplate("200", projectMapper.searchDevelopingProjectbyUsername(username));
        } catch (Exception ex) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }

    @RequestMapping(value = "develop/enroll/member/{project_id}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getEnrollMemeberbyProjectId(@PathVariable Long project_id){
        try {
            return new ResJsonTemplate("200", projectMapper.searchEnrollmemberbyProjectId(project_id));
        } catch (Exception e) {
            return new ResJsonTemplate("500", "查询失败");
        }
    }




}


