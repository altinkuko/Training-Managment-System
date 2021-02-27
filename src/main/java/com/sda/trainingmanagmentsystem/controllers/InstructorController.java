package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class InstructorController {
    @Autowired
    private InstructorService instructorService;
@GetMapping("/courses/{userId}")
public ResponseEntity<List<Course>> getCourses(@PathVariable("userId") final Long userId) throws NotFoundException {
List<Course> courses = this.instructorService.readCoursesByInstructor(userId);
return ResponseEntity.ok(courses);
}
}
