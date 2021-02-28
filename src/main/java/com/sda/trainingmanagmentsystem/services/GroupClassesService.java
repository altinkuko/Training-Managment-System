package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.GroupClasses;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.repositories.GroupClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupClassesService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private GroupClassesRepository groupClassesRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Notification postClassNotification(NotificationRequestParams notification, final Long groupClassesId) {
        Notification notification1 = this.notificationService.saveNotification(notification);
        GroupClasses groupClasses = this.groupClassesRepository.findById(groupClassesId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        notification1.setClassesNotification(groupClasses);
        return notification1;
    }

    public GroupClasses createGroupClasses(final String groupName) {
        GroupClasses groupClasses = new GroupClasses();
        groupClasses.setGroupName(groupName);
        return this.groupClassesRepository.save(groupClasses);
    }

    public GroupClasses updateGroupClass(final Long groupClassesId, final Long courseId) {
        GroupClasses groupClasses = this.groupClassesRepository.findById(groupClassesId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        groupClasses.setGroupName(groupClasses.getGroupName());
        groupClasses.setCourse(this.courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        return this.groupClassesRepository.save(groupClasses);
    }
}
