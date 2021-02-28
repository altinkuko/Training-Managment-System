package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.ParticipantApplication;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
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

    public Boolean isRegister(final Long userId, final Long courseId){
        boolean isRegister = false;
        List<ParticipantApplication> participantApplications = this.participantApplicationRepository.
                readParticipantApplication();
        for (ParticipantApplication participantApplication: participantApplications
        ) { if (participantApplication.getCourse()==this.courseRepository.findById(courseId).
                orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION))
        && participantApplication.getUser()==this.userRepository.findById(userId).
                orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)))
            return isRegister=true;
                    }
        return isRegister;
    }

    public ParticipantApplication userApplication(final Long userId, final Long courseId){
        ParticipantApplication participantApplication = new ParticipantApplication();
        participantApplication.setAccepted(false);
        participantApplication.setDate(LocalDate.now());
        participantApplication.setUser(this.userRepository.findById(userId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        participantApplication.setCourse(this.courseRepository.findById(courseId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        if (isRegister(userId,courseId) == false)
        return participantApplicationRepository.save(participantApplication);
        else return participantApplication;
    }
}
