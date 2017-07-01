package com.crazy.estimation.controller;

import com.crazy.estimation.bean.Requirement;
import com.crazy.estimation.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xuawai on 03/05/2017.
 */
@RestController
@RequestMapping(value="/estimation")
public class RequirementController {
    @Autowired
    private RequirementService requirementService;

    //增加一个新需求
    @RequestMapping(value = "/addRequirement",method = RequestMethod.GET)
    public String addRequirement() {
        String id = requirementService.add();
        return id;
    }

    //返回一条记录
    @RequestMapping(value = "/getRequirement/{id}",method = RequestMethod.GET)
    public Requirement getRequirement(@PathVariable String id) {
        return requirementService.getRequirement(id);
    }

    //返回所有记录
    @RequestMapping(value = "/getAllRequirements",method = RequestMethod.GET)
    public List<Requirement> getAllRequirements() {
        return requirementService.getAllRequirements();
    }

    //删除一条记录
    @RequestMapping(value = "/removeOneRequirement/{id}",method = RequestMethod.GET)
    public String removeOneRequirement(@PathVariable String id) {
        return requirementService.removeOneRequirement(id);
    }

}
