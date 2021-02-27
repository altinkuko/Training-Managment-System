package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.repositories.ActivitiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ActivitiesService {
    @Autowired
    private ActivitiesRepository activitiesRepository;
    public List<Activities> findActivitiesByDate (LocalDate date){
        return this.activitiesRepository.findActivitiesByDate(date);
    }
    public List<Activities> listActivitiesByGroupClasses(final Long groupClassesId){
        return this.activitiesRepository.listActivitiesByGroupClasses(groupClassesId);
    }
}
