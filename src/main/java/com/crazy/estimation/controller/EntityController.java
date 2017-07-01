package com.crazy.estimation.controller;

import com.crazy.estimation.bean.Entity;
import com.crazy.estimation.bean.RET;
import com.crazy.estimation.service.EntityService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuawai on 04/05/2017.
 */
@RestController
@RequestMapping(value="/estimation")
public class EntityController {

    @Autowired
    private EntityService entityService;

    //增加一个新事务
    @RequestMapping(value = "/addEntity/{id}", method = RequestMethod.POST)
    public void addEntity(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        Entity entity = new Entity();
        String logicalFileName = jsonObject.getString("logicalFileName");
        String logicalFieldName = jsonObject.getString("logicalFieldName");
        String logicalFileType = jsonObject.getString("logicalFileType");
        List<RET> RETs = new ArrayList<RET>();
        //--RET level
        JSONArray stepsArray = jsonObject.getJSONArray("rets");
        for (int i = 0; i < stepsArray.size(); i++) {
            RET ret = new RET();
            JSONObject retObject = (JSONObject) stepsArray.get(i);
            String RETName = retObject.getString("retname");
            String RETField = retObject.getString("retfield");
            ret.setRETName(RETName);
            ret.setRETField(RETField);
            RETs.add(ret);
        }
        entity.setLogicalFileName(logicalFileName);
        entity.setLogicalFieldName(logicalFieldName);
        entity.setRETs(RETs);
        entity.setLogicalFileType(logicalFileType);
        entityService.add(id, entity);
    }

    //增加所有新事务
    @RequestMapping(value = "/addAllEntity/{id}", method = RequestMethod.POST)
    //可以优化，一方面利用一个service的函数完成，一方面添加事务支持，防止删除完成但是添加未完成，其他同理
    public void addAllTransaction(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        entityService.deleteArray(id, "entities");

        JSONArray entitiesArray = jsonObject.getJSONArray("entities");
        for (int i = 0; i < entitiesArray.size(); i++) {
            JSONObject entityObject = (JSONObject) entitiesArray.get(i);
            addEntity(entityObject, id);
        }
    }

    //根据上一步存储的事务，初步统计出系统的逻辑文件相关内容，返回给前台
    @RequestMapping(value = "/sortOutAllEntity/{id}", method = RequestMethod.GET)
    //
    public List<Entity> sortOutAllEntity(@PathVariable String id) {
        return entityService.sortOutAllEntity(id);
    }
}
