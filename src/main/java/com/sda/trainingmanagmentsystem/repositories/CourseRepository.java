package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(value = "select c from Course c join c.groups g join g.activities a join a.instructor i where i.userId = :userId")
    List<Course> findInstructorCourses(@Param("userId")final Long userId);

}
