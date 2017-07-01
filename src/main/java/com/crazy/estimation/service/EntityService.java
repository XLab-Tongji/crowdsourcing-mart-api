package com.crazy.estimation.service;

import com.crazy.estimation.DAO.EntityDAO;
import com.crazy.estimation.DAO.RequirementDAO;
import com.crazy.estimation.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xuawai on 04/05/2017.
 */
@Service
public class EntityService {
    @Autowired
    private EntityDAO entityDAO;

    @Autowired
    private RequirementDAO requirementDAO;

    //添加一条逻辑文件
    public void add(String id, Entity entity) {
        this.entityDAO.add(id, entity);
    }

    //删除所有旧的逻辑文件
    public void deleteArray(String id, String key) {
        this.entityDAO.deleteArray(id, key);
    }

    //根据上一步存储的事务，初步统计出系统的逻辑文件相关内容
    public List<Entity> sortOutAllEntity(String id){
        List<Entity> entities = new ArrayList<Entity>();


        Map<String, Set<String>> fileMap = new HashMap<String, Set<String>>();

        Requirement requirement = this.requirementDAO.getRequirement(id);
        List<Transaction> transactions = requirement.getTransactions();
        for(int i=0; i<transactions.size(); i++){
            Transaction transaction = transactions.get(i);
            List<Step> steps = transaction.getSteps();
            for(int j=0; j<steps.size(); j++){
                Step step = steps.get(j);
                List<ConcerningDataSet> concerningDataSets = step.getConcerningDataSets();
                for(int k=0; k<concerningDataSets.size(); k++){
                    ConcerningDataSet concerningDataSet = concerningDataSets.get(k);
                    String name = concerningDataSet.getLogicalFileName();
                    String[] fields = concerningDataSet.getLogicalFieldName().split("\\s+");

                    //判断是否已经有name对应的键值对
                    if(fileMap.get(name) == null){
                        Set<String> set = new HashSet<String>();
                        fileMap.put(name, set);
                    }
                    Set<String> fieldSet = fileMap.get(name);

                    for(String field: fields){
                        fieldSet.add(field);
                    }
                }
            }
        }

        for(Map.Entry<String,Set<String>> entry: fileMap.entrySet()){
            Entity entity = new Entity();
            entity.setLogicalFileName(entry.getKey());

            StringBuilder fields = new StringBuilder("");
            Iterator i = entry.getValue().iterator();
            while(i.hasNext()){
                fields.append(i.next().toString()+" ");
            }
            entity.setLogicalFieldName(fields.toString());

            entity.setRETs(null);

            entities.add(entity);
        }




        return entities;
    }


    //读取某需求所有的entity信息
    public List<Entity> getAllEntities(String id){
        Requirement requirement = this.requirementDAO.getRequirement(id);
        return requirement.getEntities();
    }
}
