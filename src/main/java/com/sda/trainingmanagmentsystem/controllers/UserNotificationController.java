package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.UserNotification;
import com.sda.trainingmanagmentsystem.repositories.UserNotificationRepository;
import com.sda.trainingmanagmentsystem.services.UserNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller

public class UserNotificationController {
    @Autowired
    private UserNotificationService userNotificationService;
    @Autowired
    private ProfileController profileController;
    @Autowired
    private UserNotificationRepository userNotificationRepository;

    @GetMapping("/user/notification/{id}")
    public String setNotificationRead (@PathVariable("id") final Long notificationId, Model model){
        this.userNotificationService.setRead(notificationId);
        model.addAttribute("notification", this.userNotificationRepository.findById(notificationId).get());
        return "/user/notification";
    }
    @GetMapping("/notifications/{userId}")
    public String listUserNotificationForUser(@PathVariable("userId") final Long userId, Model model){
        List<UserNotification> userNotifications =
                this.userNotificationService.findNotificationByUserId(userId);
        model.addAttribute("notifications", userNotifications);
        model.addAttribute("user", this.profileController.getCurrentUser());
        return "/user/notifications";
    }
}
