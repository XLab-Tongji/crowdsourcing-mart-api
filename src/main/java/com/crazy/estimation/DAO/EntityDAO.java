package com.crazy.estimation.DAO;

import com.crazy.estimation.bean.Entity;
import com.crazy.estimation.bean.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by xuawai on 04/05/2017.
 */
@Repository
public class EntityDAO {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(String id, Entity entity){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.addToSet("entities", entity);
        mongoTemplate.upsert(query, update, Requirement.class);
    }

    public void deleteArray(String id, String key){
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset(key);
        mongoTemplate.upsert(query, update, Requirement.class);
    }
}
