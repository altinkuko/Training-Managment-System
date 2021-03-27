package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Classes;
import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.entities.Notification;
import com.sda.trainingmanagmentsystem.entities.UserNotification;
import com.sda.trainingmanagmentsystem.models.pojo.NotificationRequestParams;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import com.sda.trainingmanagmentsystem.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/class")
public class ClassesController {
    @Autowired
    private ClassesService classesService;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfileController profileController;

    @PostMapping("/notification/{classId}")
    public String postClassesNotification(@ModelAttribute("notification") final NotificationRequestParams notification,
                                          @PathVariable("classId") final Long classId,
                                          Model model) {
        List<UserNotification> userNotifications = this.classesService.postClassNotification(notification, classId);
        model.addAttribute("class", this.classesRepository.findById(classId).get());
        return "redirect:/notification/{classId}?success";
    }

    @GetMapping("/create")
    public String showCreateClassPage(@ModelAttribute("class") Classes classes, Model model){
        List<Course> courses = this.courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "/admin/createClass";
    }

    @PostMapping("/create")
    public String createClasses(@Valid @ModelAttribute("class") final Classes classes, @RequestParam("courseId") final Long courseId, BindingResult result, Model model) {
        List<Course> courses=this.courseRepository.findAll();
        model.addAttribute("courses", courses);
        Optional<Classes> existingClass = this.classesRepository.findClassesByClassName(classes.getClassName());
        if (existingClass.isPresent())
                result.rejectValue("className", "en", "There is already an class with that name");
        if (result.hasErrors()) return "/admin/createClass";
                this.classesService.createClass(classes, courseId);
        return "redirect:/class/create?success";
    }

    @GetMapping("/edit/{classId}")
    public String showClassDetail(@PathVariable("classId") final Long classId,
                                  Model model){
        Optional<Classes> classes = this.classesRepository.findById(classId);
        model.addAttribute("class", classes.get());
        model.addAttribute("courses", this.courseRepository.findAll());
        return "/admin/editClass";
    }

    @PostMapping("/update/{classId}")
    public String updateClass(@PathVariable("classId") final Long classId,
                              @RequestParam("courseId") final Long courseId,
                              @Valid @ModelAttribute("class") Classes classes, Model model, BindingResult result) {
        List<Course> courses = this.courseRepository.findAll();
        model.addAttribute("courses", courses);
        if (result.hasErrors()) return "/admin/editClass";
        this.classesService.updateClass(classId, courseId);
        return "redirect:/class/classes?success";
    }

    @GetMapping("/classes/{userId}")
    public String findClassesByInstructor(@PathVariable("userId") final Long userId, Model model){
        List<Classes> classesList = this.classesService.readClassesByInstructor(userId);
        model.addAttribute("classes", classesList);
        model.addAttribute("user", this.profileController.getCurrentUser());
        return "/instructor/classes";
    }

    @PostMapping("/inactive/{classId}")
    public String setInactiveClass(@PathVariable("classId") final Long classId, @ModelAttribute("class") final Classes classes){
        this.classesService.setInactiveClass(classId);
        return "redirect:/class/classes?success";
    }
    @PostMapping("/activate/{classId}")
    public String activateClass(@PathVariable("classId") final Long classId, @ModelAttribute("class") final Classes classes){
        this.classesService.setActiveClass(classId);
        return "redirect:/class/classes?success";
    }

    @PostMapping("/upload/{classId}")
    public ResponseEntity<Classes> uploadFile(@PathVariable("classId") final Long classId, @RequestParam("file") MultipartFile file) throws IOException {
        Classes classes = this.classesService.uploadFile(classId,file);
        return ResponseEntity.ok(classes);
    }
    @GetMapping("/classes")
    public String showAllClasses(Model model, @ModelAttribute("course") Course course){
        List<Classes> classes = this.classesRepository.findAll();
        model.addAttribute("classes", classes);
        return "/admin/classes";
    }

    @GetMapping("/class/{userId}")
    public String showStudentClass(@PathVariable("userId") final Long userId, Model model){
       Classes classes = this.classesRepository.findClassByStudent(userId);
       model.addAttribute("students", classes.getStudents());
       model.addAttribute("user", this.profileController.getCurrentUser());
       model.addAttribute("class", classes);
       return "/user/class";
    }
    @GetMapping("/{classId}")
    public String showClass(@PathVariable("classId") final Long classId, Model model){
        model.addAttribute("class", this.classesRepository.findById(classId).get());
        model.addAttribute("students", this.classesRepository.findById(classId).get().getStudents());
        return "/instructor/class";
    }
}
