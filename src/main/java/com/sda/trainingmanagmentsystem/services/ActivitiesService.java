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

    public List<Activities> findActivitiesByDate(LocalDate date) {
        return this.activitiesRepository.findActivitiesByDate(date);
    }

    public List<Activities> listActivitiesByClasses(final Long classesId) {
        return this.activitiesRepository.listActivitiesByClasses(classesId);
    }

    public Activities createActivities(final ActivitiesRequestParams activitiesRequestParams,
                                       final Long userID,
                                       final Long classesId) {
        Activities activities = new Activities();
        activities.setDate(activitiesRequestParams.getDate());
        activities.setSubject(activitiesRequestParams.getSubject());
        activities.setClasses(this.classesRepository.findById(classesId).get());
        activities.setInstructor(this.userRepository.findById(userID).get());
        return this.activitiesRepository.save(activities);
    }

    public void deleteActivities(final Long activitiesId) {
        Activities activities = this.activitiesRepository.findById(activitiesId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        activities.setClasses(null);
        activities.setInstructor(null);
        this.activitiesRepository.delete(activities);
    }

    public Activities updateActivities(final ActivitiesRequestParams activities, final Long activitiesId, final Long userId, final Long classId) {
        Activities existingActivities = this.activitiesRepository.findById(activitiesId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        existingActivities.setDate(activities.getDate());
        existingActivities.setSubject(activities.getSubject());
        existingActivities.setInstructor(this.userRepository.findById(userId).get());
        existingActivities.setClasses(this.classesRepository.findById(classId).get());
        return this.activitiesRepository.save(existingActivities);
    }
public List<Activities> listInstructorActivities(final Long userId){
        return this.activitiesRepository.findActivitiesByInstructor(userId);
}

}
