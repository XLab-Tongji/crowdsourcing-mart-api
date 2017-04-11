package com.crazy.JPA;

import com.crazy.entity.DevInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jieping on 2017-04-11.
 */
public interface DevInfoRepository extends CrudRepository<DevInfo,Long> {
    public DevInfo save(DevInfo devInfo);
    public List<DevInfo> findByProjectId(Long projectId);

}
