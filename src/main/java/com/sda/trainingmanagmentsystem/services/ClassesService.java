package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassesService {
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private NotificationService notificationService;

    public Notification postClassNotification(NotificationRequestParams notification, final Long classId) {
        Notification notification1 = this.notificationService.saveNotification(notification);
        Classes classes = this.classesRepository.findById(classId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        notification1.setClassesNotification(classes);
        return notification1;


    }
}
