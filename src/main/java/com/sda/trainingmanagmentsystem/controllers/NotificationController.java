package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.repositories.NotificationRepository;
import com.sda.trainingmanagmentsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
}
