package com.crazy.repository;

import com.crazy.entity.Requirement;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-05-03.
 */
public interface RequirementRepository extends CrudRepository<Requirement, Long> {
    Requirement findByRequirementName(String name);
    Requirement save(Requirement requirement);
}
