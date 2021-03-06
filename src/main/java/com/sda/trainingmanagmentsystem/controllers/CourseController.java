package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CourseController {
    @Autowired
    private CourseService courseService;

@PostMapping("/create/course")
    public ResponseEntity<Course> createCourse(@RequestParam("courseName") final String courseName){
        Course course = this.courseService.createCourse(courseName);
        return ResponseEntity.ok(course);
}
@PostMapping("/update/{courseId}")
    public ResponseEntity<Course> updateCourse (@PathVariable("courseId") final Long courseId, @RequestParam("courseName") final String courseName){
        Course course = this.courseService.updateCourse(courseId, courseName);
        return ResponseEntity.ok(course);
}
    @GetMapping("/courses/{userId}")
    public ResponseEntity<List<Course>> getCourses(@PathVariable("userId") final Long userId) throws NotFoundException {
        List<Course> courses = this.courseService.readCoursesByInstructor(userId);
        return ResponseEntity.ok(courses);
    }
}
