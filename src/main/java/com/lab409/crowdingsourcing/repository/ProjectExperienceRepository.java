package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.ProjectExperience;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-05-10.
 */
public interface ProjectExperienceRepository extends CrudRepository<ProjectExperience, Long> {
    ProjectExperience save(ProjectExperience projectExperience);
    List<ProjectExperience> findByAccountId(Long id);
    ProjectExperience findById(Long id);
    void delete(ProjectExperience projectExperience);
}
