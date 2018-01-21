package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.DevInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-11.
 */
public interface DevInfoRepository extends CrudRepository<DevInfo, Long> {
    DevInfo save(DevInfo devInfo);

    List<DevInfo> findByProjectId(Long projectId);
}
