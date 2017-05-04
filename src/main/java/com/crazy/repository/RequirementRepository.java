package com.crazy.repository;

import com.crazy.entity.Requirement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-05-03.
 */
public interface RequirementRepository extends CrudRepository<Requirement, Long> {
    Requirement findByRequirementName(String name);
    List<Requirement> findByState(int state);
    Requirement save(Requirement requirement);
}
