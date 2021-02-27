package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select gc from GroupClasses gc join gc.classes c join c.instructor i where i.userId = :userId")
    List<GroupClasses> findGroupClassesByInstructor(@Param("userId") final Long userId);

    @Query(value = "select cl from Classes cl join cl.instructor i where i.userId = :userId")
    List<Classes> findClassesByInstructor(@Param("userId") final Long userId);

    @Query(value = "select a from Activities a join a.classes cl where cl.classesId = :classId")
    List<Activities> findActivitiesByClasses(@Param("classId") final Long classId);
}
