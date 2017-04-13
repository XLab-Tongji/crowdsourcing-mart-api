package com.crazy.repository;

import com.crazy.entity.MartGitConnection;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by yuhao on 2017/4/13.
 */
public interface MartGitConnectionRepository extends CrudRepository<MartGitConnection, Long> {
    MartGitConnection save(MartGitConnection martGitConnection);
}
