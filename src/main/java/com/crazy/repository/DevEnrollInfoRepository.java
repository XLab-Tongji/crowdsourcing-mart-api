package com.crazy.repository;

import com.crazy.entity.DevEnrollInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-10.
 */
public interface DevEnrollInfoRepository extends CrudRepository<DevEnrollInfo, Long> {
    DevEnrollInfo save(DevEnrollInfo devEnrollInfo);

    List<DevEnrollInfo> findByProjectId(Long projectId);

    List<DevEnrollInfo> findByProjectIdAndUsername(Long projectId, String username);

    void delete(DevEnrollInfo devEnrollInfo);
}
