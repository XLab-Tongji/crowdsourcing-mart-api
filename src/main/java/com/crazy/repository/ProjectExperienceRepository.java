package com.crazy.repository;

import com.crazy.entity.ProjectExperience;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-05-10.
 */
public interface ProjectExperienceRepository extends CrudRepository<ProjectExperience, Long> {
    ProjectExperience save(ProjectExperience projectExperience);
    List<ProjectExperience> findByAccountId(Long id);
}
