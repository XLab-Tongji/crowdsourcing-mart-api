package com.crazy.controller;

import com.crazy.mapper.AccountMapper;
import com.crazy.mapper.ProjectMapper;
import com.crazy.model.DevProInfo;
import com.crazy.model.Developer;
import com.crazy.model.Project;
import com.crazy.model.Users;
import com.crazy.util.ConvertJson;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
        return new ResJsonTemplate("200",projectMapper.addProject(project.getCost(), project.getDelivery_cycle(),
                project.getWarranty_cycle(), project.getAddress(), project.getDescription(), project.getProject_user_name(),
                project.getProject_type(), project.getProject_name()));

    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate  searchproject() {
        return new ResJsonTemplate("200",projectMapper.searchProjectall());
    }


    @RequestMapping(value = "developer/enroll", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate addDevenrollInfo(@RequestBody DevProInfo devProInfo) {

//        String projectEnrolled = accountMapper.getEnrollProject(developer.getUsername());
//
//        List projectEnrolledList = developer.getProject_enroll();
//
//
//        if (projectEnrolled == null) {
//            return new ResJsonTemplate("200", accountMapper.updateProjectEnroll(convertJson.List2Json(projectEnrolledList),
//                    developer.getUsername()));
//
//
//        } else {
//
//            List lEnrolledFromDB = convertJson.Json2List(projectEnrolled);
//
//
//            for (int i = 0; i < projectEnrolledList.size(); i++) {
//                lEnrolledFromDB.add(projectEnrolledList.get(i));
//
//            }
//
//            return new ResJsonTemplate("200", accountMapper.updateProjectEnroll(convertJson.List2Json(lEnrolledFromDB),
//                    developer.getUsername()));
//
//        }

        return new ResJsonTemplate("200", projectMapper.insertDevProInfo(devProInfo.getDev_username(),
                devProInfo.getEnroll_project_id()));


    }

    @RequestMapping(value = "developer/confirm", method = RequestMethod.POST)
    public @ResponseBody ResJsonTemplate confrimProject(@RequestBody Developer developer) {

        return new ResJsonTemplate("200", accountMapper.updateDevproject(developer.getDev_project(), developer.getUsername(),
                developer.getDev_project_id()));
    }

    @RequestMapping(value ="/list/{user}",method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate getProjectListper(@PathVariable String user){
        return new ResJsonTemplate("200", projectMapper.searchProjectbycreatUser(user));
    }











}


