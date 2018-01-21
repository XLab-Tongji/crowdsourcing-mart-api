package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.DevEnrollInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jieping on 2017-04-10.
 */
@Repository
public interface DevEnrollInfoRepository extends CrudRepository<DevEnrollInfo, Long> {
    DevEnrollInfo save(DevEnrollInfo devEnrollInfo);

    List<DevEnrollInfo> findByProjectId(Long projectId);
    List<DevEnrollInfo> findByUsername(String name);
    List<DevEnrollInfo> findByProjectIdAndUsername(Long projectId, String username);

    void delete(DevEnrollInfo devEnrollInfo);
}
