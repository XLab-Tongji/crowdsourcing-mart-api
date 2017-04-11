package com.crazy.JPA;

import com.crazy.entity.Attach;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jieping on 2017-04-10.
 */

public interface AttachRepository extends CrudRepository<Attach,Long> {
    public Attach save(Attach attach);
}

