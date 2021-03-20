package com.sda.trainingmanagmentsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeController {
    @Autowired
    private ProfileController profileController;

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

}


