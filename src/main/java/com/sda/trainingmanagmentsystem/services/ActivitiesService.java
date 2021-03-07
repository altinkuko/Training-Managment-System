package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.ActivitiesRequestParams;
import com.sda.trainingmanagmentsystem.repositories.ActivitiesRepository;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
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
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private UserRepository userRepository;
    public List<Activities> findActivitiesByDate (LocalDate date){
        return this.activitiesRepository.findActivitiesByDate(date);
    }
    public List<Activities> listActivitiesByClasses(final Long classesId){
        return this.activitiesRepository.listActivitiesByClasses(classesId);
    }

    public Activities createActivities (final ActivitiesRequestParams activitiesRequestParams){
        Activities activities = new Activities();
        activities.setDate(activitiesRequestParams.getDate());
        activities.setSubject(activitiesRequestParams.getSubject());
        Classes classes = this.classesRepository.findById(activitiesRequestParams.getClassId()).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        activities.setClasses(classes);
        User user = this.userRepository.findById(activitiesRequestParams.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        activities.setInstructor(user);
        return this.activitiesRepository.save(activities);
    }

    public void deleteActivities(final Long activitiesId){
        Activities activities = this.activitiesRepository.findById(activitiesId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        activities.setClasses(null);
        activities.setInstructor(null);
        this.activitiesRepository.delete(activities);
    }

    public Activities updateActivities (final ActivitiesRequestParams activitiesRequestParams, final Long activitiesId){
        Activities activities = this.activitiesRepository.findById(activitiesId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        activities.setDate(activitiesRequestParams.getDate());
        activities.setSubject(activitiesRequestParams.getSubject());
        activities.setInstructor(this.userRepository.findById(activitiesRequestParams.getUserId()).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        activities.setClasses(this.classesRepository.findById(activitiesRequestParams.getClassId()).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        return this.activitiesRepository.save(activities);
    }

}
