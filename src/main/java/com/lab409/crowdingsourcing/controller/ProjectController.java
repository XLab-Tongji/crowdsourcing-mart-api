package com.lab409.crowdingsourcing.controller;


import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import com.lab409.crowdingsourcing.entity.DevInfo;
import com.lab409.crowdingsourcing.entity.Requirement;
import com.lab409.crowdingsourcing.repository.AccountRepository;
import com.lab409.crowdingsourcing.repository.RequirementRepository;
import com.lab409.crowdingsourcing.service.AccountService;
import com.lab409.crowdingsourcing.service.FileService;
import com.lab409.crowdingsourcing.service.ProjectService;
import com.lab409.crowdingsourcing.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@RestController
@RequestMapping(value = "api/project")
@Transactional
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RequirementRepository requirementRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResJsonTemplate addProject(@RequestParam(value = "project_type") String project_type,
                                      @RequestParam(value = "cost") double cost,
                                      @RequestParam(value = "delivery_cycle") int delivery_cycle,
                                      @RequestParam(value = "warranty_cycle") int warranty_cycle,
                                      @RequestParam(value = "address") String address,
                                      @RequestParam(value = "description") String description,
                                      @RequestParam(value = "project_name") String project_name,
                                      HttpServletRequest request) {
        Account account = accountService.getAccount(request);
        if (account == null) {
            return new ResJsonTemplate<>("404","unauthorized");
        }
        String username = account.getUsername();
        return projectService.addProject(cost, delivery_cycle, warranty_cycle, address, description, username, project_type, project_name);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate searchproject() {

        return projectService.getAllproject();
    }


    @RequestMapping(value = "/develop/enroll", method = RequestMethod.POST)
    @ResponseBody
    public ResJsonTemplate addDevenrollInfo(@RequestBody DevEnrollInfo devEnrollInfo) {
        Requirement requirement = requirementRepository.findById(devEnrollInfo.getProjectId());
        if( requirement == null){
            return new ResJsonTemplate<>("400","无列表");
        }
        devEnrollInfo.setProjectId(requirement.getProjectId());
        return projectService.addEnrollInfo(devEnrollInfo);
    }

    @RequestMapping(value = "/develop/enroll/cancel", method = RequestMethod.DELETE)
    @ResponseBody
    public ResJsonTemplate deleteEnrollInfo(@RequestParam(value = "dev_username") String dev_username,
                                            @RequestParam(value = "enroll_project_id") Long enroll_project_id) {
        return projectService.deleteEnrollInfo(dev_username, enroll_project_id);

    }

    @RequestMapping(value = "/develop/enroll/count/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getProjectEnrollcount(@PathVariable Long id) {
        return projectService.getEnrollCountByProjectId(id);

    }

    @RequestMapping(value = "/develop/enroll/list/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getEnrolledProjectbyUsername(@PathVariable String username) {

        return projectService.getEnrollListByDevUsername(username);
    }


    @RequestMapping(value = "/all/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getProjectListper(@PathVariable String username) {

        return projectService.getProjectListbyusername(username);

    }

    @RequestMapping(value = "/list/{user}/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getProjectListbyId(@PathVariable String user, @PathVariable Long id) {
        return projectService.getProjectDetailbyUsernameId(user, id);
    }


    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getProjectListOnlyId(@PathVariable Long id) {
        return projectService.getProjectDetailByProjectId(id);

    }


    @RequestMapping(value = "/develop/confirm", method = RequestMethod.POST)
    @ResponseBody
    public ResJsonTemplate confirmProject(@RequestBody DevInfo devInfo) {

        return projectService.confirmDevelop(devInfo);

    }


    @RequestMapping(value = "/develop/project/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getDevelopmember(@PathVariable Long id) {

        return projectService.getDeveloperCountbyProjectId(id);

    }


    @RequestMapping(value = "/develop/confirm/member", method = RequestMethod.POST)
    @ResponseBody
    public ResJsonTemplate confirmProjectmember(@RequestBody List<DevInfo> devInfos) {

        return projectService.confirmDevelopMember(devInfos);

    }


    @RequestMapping(value = "/develop/list/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getDevelopingProjectInfo(@PathVariable String username) {

        return projectService.getDevProjectListbyDevelopUsername(username);

    }

    @RequestMapping(value = "/develop/enroll/member/{project_id}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getEnrollMemeberbyProjectId(@PathVariable Long project_id) {
        return projectService.getDevelopUsernameListByProjectId(project_id);

    }

    @RequestMapping(value = "/develop/enroll/member/detail/{project_id}", method = RequestMethod.GET)
    @ResponseBody
    public ResJsonTemplate getEnrollMemberDetailbyProjectID(@PathVariable Long project_id) {
        return projectService.getDevelopDetailByProjectId(project_id);
    }


    @RequestMapping(value = "/page/{pageNumber}", method = RequestMethod.GET)
    public ResJsonTemplate getAllProjectByPageNumber(@PathVariable int pageNumber) {

        return projectService.getProjectListbyPageNumber(pageNumber, 10);

    }


    @RequestMapping(value = "/page/{pageSize}/{pageNumber}", method = RequestMethod.GET)
    public ResJsonTemplate getAllProjectByPageSizeAndPageNumber(@PathVariable int pageSize, @PathVariable int pageNumber) {
        return projectService.getProjectListbyPageNumber(pageNumber, pageSize);
    }


}



