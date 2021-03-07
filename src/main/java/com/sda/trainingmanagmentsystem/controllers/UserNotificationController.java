package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.UserNotification;
import com.sda.trainingmanagmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class UserNotificationController {
    @Autowired
    private UserNotificationService userNotificationService;

    @PostMapping("/notification/{id}")
    public ResponseEntity<UserNotification> setNotificationRead (@PathVariable("id") final Long notificationId){
        UserNotification userNotification = this.userNotificationService.setRead(notificationId);
        return ResponseEntity.ok(userNotification);
    }
    @GetMapping("/notifications/{userId}")
    public ResponseEntity<List<UserNotification>> listUserNotificationForUser(@PathVariable("userId") final Long userId){
        List<UserNotification> userNotifications = this.userNotificationService.findNotificationByUserId(userId);
        return ResponseEntity.ok(userNotifications);
    }
}
