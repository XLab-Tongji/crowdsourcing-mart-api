package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.Developer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-05-03.
 */
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    Developer findByUsername(String name);
    Developer save(Developer d);
}
