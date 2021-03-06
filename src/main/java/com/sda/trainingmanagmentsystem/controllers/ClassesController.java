package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClassesController {
    @Autowired
    private ClassesService classesService;

    @PostMapping("/notification/{groupClassesId}")
    public ResponseEntity<Notification> postClassesNotification(@RequestBody final NotificationRequestParams notification, @PathVariable("groupClassesId") final Long groupClassesId) {
        Notification classNotification = this.classesService.postClassNotification(notification, groupClassesId);
        return ResponseEntity.ok(classNotification);
    }

    @PostMapping("/create/groupClass")
    public ResponseEntity<Classes> createGroupClasses(@RequestParam("groupName") final String groupName) {
        Classes classes = this.classesService.createClass(groupName);
        return ResponseEntity.ok(classes);
    }

    @PostMapping("/update/groupclasses/{groupClassesId}")
    public ResponseEntity<Classes> updateGroupClasses(@PathVariable("groupClassesId") final Long groupClassesId, @RequestParam("courseId") final Long courseId) {
        Classes classes = this.classesService.updateClass(groupClassesId, courseId);
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/course/groupclasses/{userId}")
    public ResponseEntity<List<Classes>> findGroupClassesByInstructor(@PathVariable("userId") final Long userId){
        List<Classes> classesList = this.classesService.readClassesByInstructor(userId);
        return ResponseEntity.ok(classesList);
    }
}
