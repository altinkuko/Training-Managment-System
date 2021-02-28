package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
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

    @GetMapping("/course/groupclasses/{userId}")
public ResponseEntity<List<GroupClasses>> findGroupClassesByInstructor(@PathVariable("userId") final Long userId){
        List<GroupClasses> groupClassesList = this.courseService.readGroupClassesByInstructor(userId);
        return ResponseEntity.ok(groupClassesList);
    }
@GetMapping("/classes/{userId}")
    public ResponseEntity<List<Classes>> findClassesByInstructor(@PathVariable("userId") final Long userId){
        List<Classes> classes = this.courseService.readClassesByInstructor(userId);
        return ResponseEntity.ok(classes);
}
@GetMapping("/activities/{classId}")
    public ResponseEntity<List<Activities>> findActivitiesByClass (@PathVariable("classId") final Long classId){
        List<Activities> activities = this.courseService.readActivitiesByClasses(classId);
        return ResponseEntity.ok(activities);
}
@PostMapping("/create/course")
    public ResponseEntity<Course> createCourse(@RequestParam("courseName") final String courseName){
        Course course = this.courseService.createCourse(courseName);
        return ResponseEntity.ok(course);
}
}
