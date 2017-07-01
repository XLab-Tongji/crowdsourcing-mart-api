package com.crazy.estimation.controller;

import com.crazy.estimation.bean.ConcerningDataSet;
import com.crazy.estimation.bean.Step;
import com.crazy.estimation.bean.Transaction;
import com.crazy.estimation.service.TransactionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuawai on 03/05/2017.
 */
@RestController
@RequestMapping(value="/estimation")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //增加一个新事务
    @RequestMapping(value = "/addTransaction/{id}",method = RequestMethod.POST)
    public void addTransaction(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        Transaction transaction = new Transaction();
        String transactionName = jsonObject.getString("transactionName");
        String transactionType = jsonObject.getString("transactionType");
        String transactionDescription = jsonObject.getString("transactionDescription");
        String countRepeatField = jsonObject.getString("countRepeatField");
        String regulationOfSameLogic = jsonObject.getString("regulationOfSameLogic");
        String regulationOfReturningStatus = jsonObject.getString("regulationOfReturningStatus");
        List<Step> steps = new ArrayList<Step>();
        //--step level
        JSONArray stepsArray = jsonObject.getJSONArray("steps");
        for(int i=0; i<stepsArray.size(); i++){
            Step step = new Step();
            JSONObject stepObject = (JSONObject) stepsArray.get(i);
            String stepName = stepObject.getString("stepName");
            List<ConcerningDataSet> concerningDataSets = new ArrayList<ConcerningDataSet>();
            //--concerningDataSet level
            JSONArray concerningDataSetsArray = stepObject.getJSONArray("concerningDataSets");
            for(int j=0; j<concerningDataSetsArray.size(); j++){
                ConcerningDataSet concerningDataSet = new ConcerningDataSet();
                JSONObject concerningDataSetObject = (JSONObject) concerningDataSetsArray.get(j);
                String logicalFileName = concerningDataSetObject.getString("logicalFileName");
                String logicalFieldName = concerningDataSetObject.getString("logicalFieldName");
                concerningDataSet.setLogicalFileName(logicalFileName);
                concerningDataSet.setLogicalFieldName(logicalFieldName);
                concerningDataSets.add(concerningDataSet);
            }
            //--concerningDataSet level
            step.setStepName(stepName);
            step.setConcerningDataSets(concerningDataSets);
            steps.add(step);
        }
        //step level--

        transaction.setTransactionName(transactionName);
        transaction.setTransactionType(transactionType);
        transaction.setTransactionDescription(transactionDescription);
        transaction.setCountRepeatField(countRepeatField);
        transaction.setRegulationOfSameLogic(regulationOfSameLogic);
        transaction.setRegulationOfReturningStatus(regulationOfReturningStatus);
        transaction.setSteps(steps);
        transactionService.add(id, transaction);
    }


    //增加所有新事务
    @RequestMapping(value = "/addAllTransaction/{id}",method = RequestMethod.POST)
    //可以优化，一方面利用一个service的函数完成，一方面添加事务支持，防止删除完成但是添加未完成，其他同理
    public void addAllTransaction(@RequestBody JSONObject jsonObject, @PathVariable String id) {
        transactionService.deleteArray(id, "transactions");

        JSONArray transactionsArray = jsonObject.getJSONArray("transactions");
        for(int i=0; i<transactionsArray.size(); i++){
            JSONObject transactionObject = (JSONObject) transactionsArray.get(i);
            addTransaction(transactionObject, id);
        }
    }
}
