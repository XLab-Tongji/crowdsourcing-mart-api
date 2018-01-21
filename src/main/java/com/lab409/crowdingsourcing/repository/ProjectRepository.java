package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.Developer;
import com.lab409.crowdingsourcing.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-10.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project save(Project project);

    List<Project> findAll();

    List<Project> findByUsername(String username);

    Project findByProjectId(Long project_id);
    List<Project> findByProjectIdAndUsername(Long project_id, String username);

    long count();
    //    @Query(value = "SELECT * FROM project where username = ?1",
//            nativeQuery = true)
    @Query("SELECT a FROM Project a , DevEnrollInfo b WHERE a.projectId=b.projectId AND b.username=?1")
//    @Query("select a from Project as a where a.username = ?1")
    List<Project> searchEnrollProjectByUsername(String username);

    @Query("SELECT a FROM Project a, DevInfo b WHERE a.projectId=b.projectId AND b.username=?1")
        //   @Query("select all from Project project, DevInfo info where project.username=?1")
    List<Project> searchDevelopingProjectbyUsername(String username);

    @Query("SELECT b FROM DevEnrollInfo a , Developer b WHERE a.username=b.username AND a.projectId=?1")
        //  @Query("select all from Developer developer,DevEnrollInfo info where info.project_id=?1")
    List<Developer> searchDeveloperEnrollInfo(Long projectId);

}
