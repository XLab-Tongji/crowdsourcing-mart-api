package com.lab409.crowdingsourcing.repository;

import com.lab409.crowdingsourcing.entity.Account;
import com.lab409.crowdingsourcing.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TestRepository extends JpaRepository<Account, Long> {

//    @Query("select u from Account u where u.username = ?1")
//    Account findByEmailAddress(String username);
    @Query("select a from Project a where a.username = ?1")
    List<Project> findByEmailAddress(String username);
}
