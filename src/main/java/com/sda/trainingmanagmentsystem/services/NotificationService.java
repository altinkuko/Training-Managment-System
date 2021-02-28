package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.repositories.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
public Notification saveNotification(final NotificationRequestParams notificationRequestParams){
    Notification notification = new Notification();
    notification.setDate(notificationRequestParams.getDate());
    notification.setContent(notificationRequestParams.getContent());
    notification.setSubject(notificationRequestParams.getSubject());
    return this.notificationRepository.save(notification);
}

}
