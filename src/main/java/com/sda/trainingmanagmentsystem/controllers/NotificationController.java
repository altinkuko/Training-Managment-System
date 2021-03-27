package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ClassesRepository classesRepository;

    @GetMapping("/notification/{classId}")
    public String showNotificationPage(@PathVariable("classId") final Long classId,
                                       @ModelAttribute("notification") NotificationRequestParams notification,
                                       Model model){
  model.addAttribute("class", this.classesRepository.findById(classId).get());
        return "/instructor/createNotification";
    }

}
