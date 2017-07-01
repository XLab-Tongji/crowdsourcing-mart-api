package com.crazy.estimation.DAO;

import com.crazy.estimation.bean.Requirement;
import com.crazy.estimation.bean.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by xuawai on 03/05/2017.
 */
@Repository
public class TransactionDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    //向数组中增添一个对象，如果数组不存在，将被创建
    public void add(String id, Transaction transaction){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.addToSet("transactions", transaction);
        mongoTemplate.upsert(query, update, Requirement.class);
    }

    //这个函数可以提到基类中去
    //删除以key为键的数组对象，其中，键也会被删除
    public void deleteArray(String id, String key){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset(key);
        mongoTemplate.upsert(query, update, Requirement.class);
    }


}
