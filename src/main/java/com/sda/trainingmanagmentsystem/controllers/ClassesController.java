package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.services.ClassesService;
import com.sda.trainingmanagmentsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private NotificationService notificationService;
    @PostMapping("/notification/{classesId}")
    public ResponseEntity<Notification> postClassesNotification(@RequestBody final NotificationRequestParams notification,@PathVariable("classesId") final Long classesId){
        Notification classNotification = this.classesService.postClassNotification(notification,classesId);
        return ResponseEntity.ok(classNotification);
    }

}
