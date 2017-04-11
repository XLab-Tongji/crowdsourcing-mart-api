package com.crazy.JPA;

import com.crazy.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-10.
 */
public interface ProjectRepository extends CrudRepository<Project,Long>,PagingAndSortingRepository<Project,Long> {
    public  Project save(Project project);
    public List<Project> findAll();
    public List<Project> findByUsername(String username);
    public List<Project> findByProjectId(Long project_id);
    public List<Project> findByProjectIdAndUsername(Long project_id,String username);
    public 	long count();
 }
