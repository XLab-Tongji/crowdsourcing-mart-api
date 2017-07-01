package com.crazy.estimation.controller;

import com.crazy.estimation.bean.Description;
import com.crazy.estimation.service.DescriptionService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xuawai on 03/05/2017.
 */
@RestController
@RequestMapping(value="/estimation")
public class DescriptionController {


    @Autowired
    private DescriptionService descriptionService;

    //添加描述信息或更新现有的描述信息
    @RequestMapping(value = "/addDescription/{id}",method = RequestMethod.POST)
    public void addDescription(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        String projectName = jsonObject.getString("projectName");
        String projectDescription = jsonObject.getString("projectDescription");
        Description description = new Description();
        description.setProjectName(projectName);
        description.setProjectDescription(projectDescription);
        descriptionService.add(id, description);
    }


}
