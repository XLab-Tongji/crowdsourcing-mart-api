package com.crazy.repository;

import com.crazy.entity.Requirement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jieping on 2017-05-03.
 */
@Repository
public interface RequirementRepository extends CrudRepository<Requirement, Long> {
    List<Requirement> findByCreatorId(Long id);

    Requirement findByRequirementName(String name);
    Requirement findById(Long id);
    Requirement save(Requirement requirement);

    @Transactional(readOnly = true)
    int deleteById(Long id);

    boolean exists(Long id);

    List<Requirement> getRequirementsByRequirementType(String state);
}
