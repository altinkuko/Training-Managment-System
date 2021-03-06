package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesService {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private CourseRepository courseRepository;

    public Notification postClassNotification(NotificationRequestParams notification, final Long classId) {
        Notification notification1 = this.notificationService.saveNotification(notification);
        Classes classes = this.classesRepository.findById(classId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        notification1.setClassesNotification(classes);
        return notification1;
    }

    public Classes createClass(final String className) {
        Classes classes = new Classes();
        classes.setClassName(className);
        return this.classesRepository.save(classes);
    }

    public Classes updateClass(final Long classesId, final Long courseId) {
        Classes classes = this.classesRepository.findById(classesId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        classes.setClassName(classes.getClassName());
        classes.setCourse(this.courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        return this.classesRepository.save(classes);
    }
    public List<Classes> readClassesByInstructor(final Long userId) {
        return this.classesRepository.findClassesByInstructor(userId);
    }
}
