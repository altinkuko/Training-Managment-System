package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String createCourse(@Valid @ModelAttribute("course") Course course, BindingResult result){
    Optional<Course> optionalCourse = this.courseRepository.findCourseByCourseName(course.getCourseName());
    if (optionalCourse.isPresent())
        result.rejectValue("courseName", "en", "There is already an course with that name");
    if (result.hasErrors()) return "/admin/createCourse";
    this.courseService.createCourse(course);
        return "redirect:/course/create?success";
}
@PostMapping("/update/{courseId}")
    public String updateCourse (@PathVariable("courseId") final Long courseId, @Valid @ModelAttribute("course") Course course, BindingResult result){
       if (result.hasErrors()) return "admin/editCourse";
        this.courseService.updateCourse(courseId, course);
        return "redirect:/course/{courseId}?success";
}
    @GetMapping("/courses/{userId}")
    public ResponseEntity<List<Course>> getCourses(@PathVariable("userId") final Long userId) throws NotFoundException {
        List<Course> courses = this.courseService.readCoursesByInstructor(userId);
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/courses")
    public String listAllCourses (Model model){
        List<Course> courses = this.courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "/admin/courses";
    }
    @GetMapping("/{courseId}")
    public String showCourseInfo(@PathVariable("courseId") final Long courseId, Model model){
        Optional<Course> course = this.courseRepository.findById(courseId);
        model.addAttribute("course", course.get());
        return "admin/editCourse";
    }
}
