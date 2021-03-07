package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u from User u inner join u.role r where r.roleId = :roleId")
    List<User> findUsersByRole(@Param("roleId") final Long roleId);
    @Query(value = "select u from User u inner join u.classes c where c.classesId = :classesId")
    List<User> findUsersByClass(@Param(("classesId")) final Long classesId);

    User findByUsername(final String username);

}
