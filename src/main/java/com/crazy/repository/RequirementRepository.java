package com.crazy.repository;

import com.crazy.entity.Requirement;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-05-03.
 */
public interface RequirementRepository extends CrudRepository<Requirement, Long> {
    List<Requirement> findByCreatorId(Long id);
    Requirement findByRequirementName(String name);

    Requirement save(Requirement requirement);

    int deleteById(Long id);

    boolean exists(Long id);

    List<Requirement> getRequirementsByRequirementType(String state);
}
