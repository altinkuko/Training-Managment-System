package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, Long> {
    @Query(value = "select a from Activities a where a.date = :dates")
    public List<Activities> findActivitiesByDate(@Param("dates") final LocalDate date);

    @Query(value = "select a from Activities a join a.classes c join c.group g where g.groupClassesId = :groupClassesId")
    public List<Activities> listActivitiesByGroupClasses(@Param("groupClassesId")final Long groupClassesId);
}
