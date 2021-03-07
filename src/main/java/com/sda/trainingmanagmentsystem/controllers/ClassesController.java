package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.entities.UserNotification;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/class/")
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @PostMapping("/notification/{classId}")
    public ResponseEntity<List<UserNotification>> postClassesNotification(@RequestBody final NotificationRequestParams notification, @PathVariable("classId") final Long classId) {
        List<UserNotification> userNotifications = this.classesService.postClassNotification(notification, classId);
        return ResponseEntity.ok(userNotifications);
    }

    @PostMapping("/create")
    public ResponseEntity<Classes> createClasses(@RequestParam("className") final String className) {
        Classes classes = this.classesService.createClass(className);
        return ResponseEntity.ok(classes);
    }

    @PostMapping("/update/{classId}")
    public ResponseEntity<Classes> updateClasses(@PathVariable("classId") final Long classId, @RequestParam("courseId") final Long courseId) {
        Classes classes = this.classesService.updateClass(classId, courseId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/classes/{userId}")
    public ResponseEntity<List<Classes>> findClassesByInstructor(@PathVariable("userId") final Long userId){
        List<Classes> classesList = this.classesService.readClassesByInstructor(userId);
        return ResponseEntity.ok(classesList);
    }
    @PostMapping("/inactive/{classId}")
    public ResponseEntity<Classes> setInactiveClass(@PathVariable("classId") final Long classId){
        Classes classes = this.classesService.setInactiveClass(classId);
        return ResponseEntity.ok(classes);
    }
    @PostMapping("/activate/{classId}")
    public ResponseEntity<Classes> activateClass(@PathVariable("classId") final Long classId){
        Classes classes = this.classesService.setActiveClass(classId);
        return ResponseEntity.ok(classes);
    }

    @PostMapping("/upload/{classId}")
    public ResponseEntity<Classes> uploadFile(@PathVariable("classId") final Long classId, @RequestParam("file") MultipartFile file) throws IOException {
        Classes classes = this.classesService.uploadFile(classId,file);
        return ResponseEntity.ok(classes);
    }
}
