package com.crazy.repository;

import com.crazy.entity.Developer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-05-03.
 */
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    Developer findByUsername(String name);
    Developer save(Developer d);
}
