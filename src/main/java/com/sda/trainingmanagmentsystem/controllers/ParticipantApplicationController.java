package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.ParticipantApplication;
import com.sda.trainingmanagmentsystem.services.ParticipantApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParticipantApplicationController {
    @Autowired
    private ParticipantApplicationService participantApplicationService;

    @PostMapping("/application/{userId}")
    public ResponseEntity<ParticipantApplication> studentApplication(@PathVariable("userId") Long userId, @RequestParam("courseId") Long courseId){
       ParticipantApplication participantApplication= this.participantApplicationService.userApplication(userId,courseId);
  return ResponseEntity.ok(participantApplication);
    }
@GetMapping("/application/{userId}")
    public ResponseEntity<Boolean> isRegister(@PathVariable("userId") final Long userId, @RequestParam final Long courseId){
        boolean isRegister = this.participantApplicationService.isRegister(userId,courseId);
        return ResponseEntity.ok(isRegister);
}

}
