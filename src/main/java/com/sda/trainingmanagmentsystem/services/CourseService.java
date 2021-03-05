package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ClassesRepository classesRepository;


    public Course createCourse(final String courseName) {
        Course course = new Course();
        course.setCourseName(courseName);
        return this.courseRepository.save(course);
    }

    public Course updateCourse(final Long courseId, final String courseName) {
        Course course = this.courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        course.setCourseName(courseName);
        return this.courseRepository.save(course);
    }

    public List<Course> readCoursesByInstructor(final Long userId) {
        return this.courseRepository.findInstructorCourses(userId);
    }


}
