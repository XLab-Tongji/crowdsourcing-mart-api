package com.lab409.crowdingsourcing.repository;

        import com.lab409.crowdingsourcing.entity.Role;
        import org.springframework.data.repository.CrudRepository;

        import java.util.List;

/**
 * Created by yuhao on 2017/4/19.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
    List<Role> findByRoleId(Long roleId);
}
