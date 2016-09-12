package com.crazy.controller;

import com.crazy.mapper.ProjectMapper;
import com.crazy.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by SHIKUN on 2016/9/12.
 */
@RestController
@RequestMapping(value = "/project")
@Transactional
public class ProjectController {

    @Autowired
    private ProjectMapper projectMapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public int addProject(@RequestBody Project project) {
        return projectMapper.addProject(project.getCost(), project.getDelivery_cycle(), project.getWarranty_cycle(),
                project.getAddress(), project.getDescription(), project.getUser_name(), project.getProject_type());
    }

    @RequestMapping(method = RequestMethod.GET)
    public Project searchproject() {
        return projectMapper.searchProjectall();
    }

    



}

