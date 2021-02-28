package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.services.GroupClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class GroupClassesController {
    @Autowired
    private GroupClassesService groupClassesService;
    @PostMapping("/notification/{groupClassesId}")
    public ResponseEntity<Notification> postClassesNotification(@RequestBody final NotificationRequestParams notification, @PathVariable("groupClassesId") final Long groupClassesId){
        Notification classNotification = this.groupClassesService.postClassNotification(notification,groupClassesId);
        return ResponseEntity.ok(classNotification);
    }
}
