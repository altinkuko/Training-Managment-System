package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {
    @Query(value = "select c from Classes c join c.activities a join a.instructor i where i.userId = :userId")
    List<Classes> findClassesByInstructor(@Param("userId") final Long userId);


}
