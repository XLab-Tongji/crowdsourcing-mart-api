package com.crazy.estimation.service;

import com.crazy.estimation.DAO.RequirementDAO;
import com.crazy.estimation.bean.Description;
import com.crazy.estimation.bean.Entity;
import com.crazy.estimation.bean.Requirement;
import com.crazy.estimation.bean.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuawai on 03/05/2017.
 */
@Service
public class RequirementService {
    @Autowired
    private RequirementDAO requirementDAO;

    public String add(){


        //--requirement level
        Requirement requirement = new Requirement();
        long time = System.currentTimeMillis();
        String id = String.valueOf(time);
             //--description level
        Description description = new Description();
        description.setProjectName("");
        description.setProjectDescription("");
             //description level--
        List<Transaction> transactions = new ArrayList<Transaction>();
//             //--transaction level
//        Transaction transaction = new Transaction();
//        transaction.setTranscationName("");
//        transaction.setTranscationType("");
//        transaction.setTranscationDescription("");
//        List<Step> steps = new ArrayList<Step>();
//                 //--step level
//        Step step = new Step();
//        List<ConcerningDataSet> concerningDataSets = new ArrayList<ConcerningDataSet>();
//                     //--concerningDataSet level-
//        ConcerningDataSet concerningDataSet = new ConcerningDataSet();
//        concerningDataSet.setLogicalFileName("");
//        concerningDataSet.setLogicalFieldName("");
//                     //concerningDataSet level--
//        concerningDataSets.add(concerningDataSet);
//        step.setConcerningDataSets(concerningDataSets);
//        step.setStepName("");
//                 //step level--
//        steps.add(step);
//        transaction.setSteps(steps);
//             //transaction level--
//        transactions.add(transaction);
               //entity level--
        List<Entity> entities = new ArrayList<Entity>();
               //--entity level
        requirement.setId(id);
        requirement.setDescription(description);
        requirement.setTransactions(transactions);
        requirement.setEntities(entities);
        //requirement level--



        this.requirementDAO.add(requirement);
        //这里应该修改为，如果插入成功，则返回id
        return id;
    }

    public Requirement getRequirement(String id){
        return requirementDAO.getRequirement(id);
    }

    public List<Requirement> getAllRequirements(){
        return requirementDAO.getAllRequirements();
    }

    public String removeOneRequirement(String id){
        return requirementDAO.removeOneRequirement(id);
    }
}
