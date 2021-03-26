package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.ParticipantApplication;
import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.repositories.ParticipantApplicationRepository;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ParticipantApplicationService {
    @Autowired
    private ParticipantApplicationRepository participantApplicationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ClassesRepository classesRepository;

    public Boolean isRegister(final Long userId, final Long courseId) {
        boolean isRegister = false;
        List<ParticipantApplication> participantApplications = this.participantApplicationRepository.
                readUnacceptedParticipantApplication();
        for (ParticipantApplication participantApplication : participantApplications
        ) {
            if (participantApplication.getCourse() == this.courseRepository.findById(courseId).
                    orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION))
                    && participantApplication.getUser() == this.userRepository.findById(userId).
                    orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)))
                return isRegister = true;
        }
        return isRegister;
    }

    public ParticipantApplication userApplication(final Long userId, final Long courseId) {
        ParticipantApplication participantApplication = new ParticipantApplication();
        participantApplication.setAccepted(false);
        participantApplication.setDate(LocalDate.now());
        participantApplication.setUser(this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        participantApplication.setCourse(this.courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        if (isRegister(userId, courseId) == false)
            return participantApplicationRepository.save(participantApplication);
        else return participantApplication;
    }

    public ParticipantApplication acceptApplication(final Long applicationId, final Long userId, final Long classId) {
        ParticipantApplication participantApplication = this.participantApplicationRepository.findById(applicationId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        participantApplication.setAccepted(true);
        User user=this.userRepository.findById(userId).get();
        user.setClasses(this.classesRepository.findById(classId).get());
        return this.participantApplicationRepository.save(participantApplication);
    }
    public List<ParticipantApplication> listUnacceptedApplication(){
        return this.participantApplicationRepository.readUnacceptedParticipantApplication();
    }
    public void deleteApplication(final Long applicationId){
        ParticipantApplication participantApplication = this.participantApplicationRepository.findById(applicationId).orElseThrow(()->new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        participantApplication.setUser(null);
        participantApplication.setCourse(null);
        this.participantApplicationRepository.delete(participantApplication);
    }
}
