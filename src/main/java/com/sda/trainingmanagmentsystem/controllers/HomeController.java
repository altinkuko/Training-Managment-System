package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Course;
import com.sda.trainingmanagmentsystem.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
class HomeController {
    @Autowired
    private ProfileController profileController;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/")
    String homePage() {return "home/homeNotSignedIn"; }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/home")
    public String homePageLoggedIn(Model model)
    {model.addAttribute("user", this.profileController.getCurrentUser());
        return "home/homeSignedIn";
    }
    @GetMapping("/admin/controlPanel")
    public String controlPanel(Model model){
        model.addAttribute("user", this.profileController.getCurrentUser());
        return "admin/controlPanel";
    }
    @GetMapping("/courses/")
    public String listAllCourses (Model model){
        List<Course> courses = this.courseRepository.findAll();
        model.addAttribute("courses", courses);
        model.addAttribute("user", this.profileController.getCurrentUser());
        return "/home/courses";
    }
@GetMapping("/instructor/panel")
    public String instructorPanel(Model model){
        model.addAttribute("user", this.profileController.getCurrentUser());
        return "instructor/instructorPanel";
}

}


