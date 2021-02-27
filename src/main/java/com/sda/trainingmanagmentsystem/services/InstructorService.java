package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
import com.sda.trainingmanagmentsystem.repositories.InstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

public List<Course> readCoursesByInstructor(final Long userId){
return this.instructorRepository.findInstructorCourses(userId);
}
}
