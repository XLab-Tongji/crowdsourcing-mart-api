package com.crazy.repository;

import com.crazy.entity.Developer;
import com.crazy.entity.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-10.
 */
public interface ProjectRepository extends CrudRepository<Project, Long>, PagingAndSortingRepository<Project, Long> {
    Project save(Project project);

    List<Project> findAll();

    List<Project> findByUsername(String username);

    List<Project> findByProjectId(Long project_id);

    List<Project> findByProjectIdAndUsername(Long project_id, String username);

    long count();
    @Query("SELECT a FROM Project a , DevEnrollInfo b WHERE a.projectId=b.projectId AND b.username=?1")
        //@Query("SELECT  from Project project,DevEnrollInfo info where project.username=?1")
    List<Project> searchEnrollProjectByUsername(String username);

    @Query("SELECT a FROM Project a, DevInfo b WHERE a.projectId=b.projectId AND b.username=?1")
        //   @Query("select all from Project project, DevInfo info where project.username=?1")
    List<Project> searchDevelopingProjectbyUsername(String username);

    @Query("SELECT b FROM DevEnrollInfo a , Developer b WHERE a.username=b.username AND a.projectId=?1")
        //  @Query("select all from Developer developer,DevEnrollInfo info where info.project_id=?1")
    List<Developer> searchDeveloperEnrollInfo(Long projectId);

}
