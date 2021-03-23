package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseService courseService;

    @GetMapping("/create")
    public String showCreateCoursePage(@ModelAttribute("course") Course course){
        return "admin/createCourse"; }

@PostMapping("/create")
    public String createCourse(@ModelAttribute("course") Course course, BindingResult result){
    Optional<Course> optionalCourse = this.courseRepository.findCourseByCourseName(course.getCourseName());
    if (optionalCourse.isPresent())
        result.rejectValue("courseName", "en", "There is already an course with that name");
    if (result.hasErrors()) return "redirect:admin/createCourse";
    this.courseService.createCourse(course);
        return "redirect:admin/createCourse?success";
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
