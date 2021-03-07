package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.entities.UserNotification;
import com.sda.trainingmanagmentsystem.repositories.UserNotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class UserNotificationService {
    @Autowired
    private UserNotificationRepository userNotificationRepository;

    public UserNotification setRead(final Long userNotificationId){
        UserNotification userNotification = this.userNotificationRepository.findById(userNotificationId).orElseThrow();
        userNotification.setIsRead(true);
        return this.userNotificationRepository.save(userNotification);
    }
    public List<UserNotification> findNotificationByUserId(final Long userId){
        return this.userNotificationRepository.listNotificationForUser(userId);
    }
}
