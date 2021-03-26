package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.ParticipantApplication;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.repositories.ParticipantApplicationRepository;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
import com.sda.trainingmanagmentsystem.services.ParticipantApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/application/")
public class ParticipantApplicationController {
    @Autowired
    private ParticipantApplicationService participantApplicationService;
    @Autowired
    private ParticipantApplicationRepository participantApplicationRepository;
    @Autowired
    private ProfileController profileController;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}")
    public String studentApplication(@PathVariable("userId") Long userId, @RequestParam("courseId") Long courseId, Model model) {
        if (this.participantApplicationService.isRegister(userId, courseId)) return "redirect:/courses/?error";
        ParticipantApplication participantApplication = this.participantApplicationService.userApplication(userId, courseId);
        model.addAttribute("applicationForm", participantApplication);
        return "redirect:/courses/?success";
    }

    @GetMapping
    public String afterApp(Model model) {
        model.addAttribute("user", this.profileController.getCurrentUser());
        model.addAttribute("courses", this.courseRepository.findAll());
        return "/home/courses";
    }

    @PostMapping("/confirm/{id}")
    public String confirmApplication(@PathVariable("id") final Long applicationId,
                                     @RequestParam("userId") final Long userId,
                                     @RequestParam("classId") final Long classId) {
        this.participantApplicationService.acceptApplication(applicationId, userId, classId);
        return "redirect:/application/unaccepted?accepted";
    }

    @GetMapping("/accept/{id}")
    public String showApplicationForm(@PathVariable("id") Long applicationId,
                                      @RequestParam("userId") Long userId,
                                      @RequestParam("courseId") Long courseId,
                                      Model model) {
        ParticipantApplication participantApplication = this.participantApplicationRepository.findById(applicationId).get();
        model.addAttribute("app", participantApplication);
        model.addAttribute("user", this.userRepository.findById(userId).get());
        Course course = this.courseRepository.findById(courseId).get();
        model.addAttribute("course", course);
        List<Classes> classes = course.getGroups();
        model.addAttribute("classes", classes);
        return "/admin/assignClass";
    }

    @GetMapping("/unaccepted")
    public String listUnacceptedApplication(Model model) {
        List<ParticipantApplication> participantApplications = this.participantApplicationService.listUnacceptedApplication();
        model.addAttribute("applications", participantApplications);
        return "/admin/applications";
    }

    @GetMapping("/delete/{applicationId}")
    public String deleteParticipantApplication(@PathVariable("applicationId") final Long applicationId, Model model) {
        this.participantApplicationService.deleteApplication(applicationId);
        model.addAttribute("applications", this.participantApplicationRepository.findAll());
        return "redirect:/application/unaccepted?deleted";
    }

}
