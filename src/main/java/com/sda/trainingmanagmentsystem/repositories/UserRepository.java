package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u from User u inner join u.role r where r.role = :role")
    List<User> findStudents(@Param("role") final String role);

}
