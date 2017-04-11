package com.crazy.repository;

import com.crazy.entity.test;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-09.
 */
public interface testRepository extends CrudRepository<test,Long> {
    public test findById(Long id);
    public test save(test t);
    public void delete(test t);
    public test findByUsername(String username);



}
