package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<GroupClasses> readGroupClassesByInstructor(final Long userId) {
        return this.courseRepository.findGroupClassesByInstructor(userId);
    }
    public List<Classes> readClassesByInstructor(final Long userId){
        return this.courseRepository.findClassesByInstructor(userId);
    }
    public List<Activities> readActivitiesByClasses (final Long classId){
        return this.courseRepository.findActivitiesByClasses(classId);
    }
}
