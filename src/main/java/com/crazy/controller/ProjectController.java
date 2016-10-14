package com.crazy.controller;

import com.crazy.mapper.ProjectMapper;
import com.crazy.model.Project;
import com.crazy.model.Users;
import com.crazy.util.ResJsonTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public @ResponseBody ResJsonTemplate  addProject(@RequestBody Project project) {
        return new ResJsonTemplate("200",projectMapper.addProject(project.getCost(), project.getDelivery_cycle(), project.getWarranty_cycle(),
                project.getAddress(), project.getDescription(), project.getProject_user_name(), project.getProject_type(),project.getCreate_date()));
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody ResJsonTemplate  searchproject() {
        return new ResJsonTemplate("200",projectMapper.searchProjectall());
    }

    @RequestMapping(value = "/claim", method = RequestMethod.PUT)
    public int claimProject(@RequestBody Map body) {
        return projectMapper.claimProject((String) body.get("username"), Long.valueOf((Integer) body.get("id")));

    }

}


