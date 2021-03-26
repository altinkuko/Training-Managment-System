package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.pojo.ActivitiesRequestParams;
import com.sda.trainingmanagmentsystem.repositories.ActivitiesRepository;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
import com.sda.trainingmanagmentsystem.services.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/activities")
public class ActivitiesController {
    @Autowired
    private ActivitiesService activitiesService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private ActivitiesRepository activitiesRepository;
    @Autowired
    private ProfileController profileController;

    @GetMapping("/module/{date}")
    public ResponseEntity<List<Activities>> listActivitiesByDate(@PathVariable("date") LocalDate date) {
        List<Activities> activities = this.activitiesService.findActivitiesByDate(date);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/modules/{gcId}")
    public String listActivitiesByGroupClasses(@PathVariable("gcId") final Long classId,
                                               Model model) {
        List<Activities> activities = this.activitiesService.listActivitiesByClasses(classId);
        model.addAttribute("activities", activities);
        model.addAttribute("class", this.classesRepository.findById(classId).get());
        return "/user/activities";
    }

    @GetMapping("/all")
    public String listAllModules(Model model){
        model.addAttribute("modules", this.activitiesRepository.findAll());
        return "/admin/modules";
    }

    @GetMapping("/create")
    public String showCreateActivities(Model model){
        model.addAttribute("users", this.userRepository.findUsersByRole(2L));
        model.addAttribute("classes", this.classesRepository.findAll());
        model.addAttribute("activities", new Activities());
        return "admin/createActivities";
    }

    @PostMapping("/create")
    public String createActivities (@Valid final ActivitiesRequestParams activitiesRequestParams,
                                                        @RequestParam("classId") Long classId,
                                                        @RequestParam("instructorId")Long userId,
                                                        BindingResult result, Model model){
        if (result.hasErrors()) return "/admin/createActivities";
        this.activitiesService.createActivities(activitiesRequestParams, userId, classId);
        model.addAttribute("activities", activitiesRequestParams);
        model.addAttribute("users", this.userRepository.findUsersByRole(2L));
        model.addAttribute("classes", this.classesRepository.findAll());
        return "redirect:/activities/create?success";
    }
    @PostMapping("/delete/{activitiesId}")
    public String deleteActivities(@PathVariable("activitiesId") final Long activitiesId){
        this.activitiesService.deleteActivities(activitiesId);
        return "redirect:/activities/all?delete";
    }
    @PostMapping("/update/{activitiesId}")
    public String updateActivities(@PathVariable("activitiesId") final Long activitiesId,
                                   @RequestParam("classId") Long classId,
                                   @RequestParam("instructorId")Long userId,
                                   @Valid @ModelAttribute("module") Activities activities,
                                   Model model, BindingResult result){
        if (result.hasErrors()) return "/admin/editActivities";
        model.addAttribute("users", this.userRepository.findUsersByRole(2L));
        model.addAttribute("classes", this.classesRepository.findAll());
        this.activitiesService.updateActivities(activitiesId, userId, classId);
        return "redirect:/activities/all?success";
    }

    @GetMapping("/update/{activitiesId}")
    public String showEditPage(@PathVariable("activitiesId") Long activitiesId, Model model){
                model.addAttribute("module", this.activitiesRepository.findById(activitiesId).get());
                model.addAttribute("users", this.userRepository.findUsersByRole(2L));
                model.addAttribute("classes", this.classesRepository.findAll());
                return "/admin/editActivities";
    }

    @GetMapping("/instructor/{userId}")
    public String showInstructorClasses(@PathVariable("userId") final Long userId, Model model){
        List<Activities> activities = this.activitiesService.listInstructorActivities(userId);
        model.addAttribute("activities", activities);
        model.addAttribute("user", this.profileController.getCurrentUser());
        return "/instructor/classes";
    }
}
