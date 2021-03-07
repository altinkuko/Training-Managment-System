package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.ParticipantApplication;
import com.sda.trainingmanagmentsystem.services.ParticipantApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.util.List;

@Controller
@RequestMapping("/application/")
public class ParticipantApplicationController {
    @Autowired
    private ParticipantApplicationService participantApplicationService;

    @PostMapping("/{userId}")
    public ResponseEntity<ParticipantApplication> studentApplication(@PathVariable("userId") Long userId, @RequestParam("courseId") Long courseId) {
        ParticipantApplication participantApplication = this.participantApplicationService.userApplication(userId, courseId);
        return ResponseEntity.ok(participantApplication);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Boolean> isRegister(@PathVariable("userId") final Long userId, @RequestParam final Long courseId) {
        boolean isRegister = this.participantApplicationService.isRegister(userId, courseId);
        return ResponseEntity.ok(isRegister);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<ParticipantApplication> confirmApplication(@PathVariable("id") final Long applicationId) {
        ParticipantApplication participantApplication = this.participantApplicationService.acceptApplication(applicationId);
        return ResponseEntity.ok(participantApplication);
    }
    @GetMapping("/unaccepted")
    public ResponseEntity<List<ParticipantApplication>> listUnacceptedApplication(){
        List<ParticipantApplication> participantApplications = this.participantApplicationService.listUnacceptedApplication();
        return ResponseEntity.ok(participantApplications);
    }
    @DeleteMapping("/delete/{applicationId}")
    public ResponseEntity<Void> deleteParticipantApplication(@PathVariable("applicationId") final Long applicationId){
        this.participantApplicationService.deleteApplication(applicationId);
        return ResponseEntity.ok().build();
    }

}
