package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.repositories.GroupClassesRepository;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GroupClassesRepository groupClassesRepository;

    public List<GroupClasses> readGroupClassesByInstructor(final Long userId) {
        return this.courseRepository.findGroupClassesByInstructor(userId);
    }
    public List<Classes> readClassesByInstructor(final Long userId){
        return this.courseRepository.findClassesByInstructor(userId);
    }
    public List<Activities> readActivitiesByClasses (final Long classId){
        return this.courseRepository.findActivitiesByClasses(classId);
    }
    public Course createCourse(final String courseName){
        Course course = new Course();
        course.setCourseName(courseName);
        return this.courseRepository.save(course);
    }
    public Course updateCourse(final Long courseId){
        Course course = this.courseRepository.findById(courseId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
       course.setCourseName(course.getCourseName());
       return course;
     }

}
