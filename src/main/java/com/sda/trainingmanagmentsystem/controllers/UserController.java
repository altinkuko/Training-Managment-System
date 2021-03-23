package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.UserRequestParams;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
import com.sda.trainingmanagmentsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String showRegisterPage(User user) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerTheUser(@Valid @ModelAttribute("user") UserRequestParams userRequestParams, BindingResult result) {
        Optional<User> optionalUser = userRepository.findByUsername(userRequestParams.getUsername());
        if (optionalUser.isPresent()) {
            result.rejectValue("username", "en", "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userRequestParams.setPassword(passwordEncoder.encode(userRequestParams.getPassword()));
        userService.registerUser(userRequestParams);
        return "redirect:/registration?success";
    }

    @GetMapping("/admin/registerInstructor")
    public String showInstructorRegisterPage(@ModelAttribute("user") User user) {
        return "admin/registerInstructor";
    }

    @PostMapping("/register/instructor")
    public String postInstructor(@Valid @ModelAttribute("user") final UserRequestParams userRequestParams, BindingResult result) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(userRequestParams.getUsername());
        if (optionalUser.isPresent()) {
            result.rejectValue("username", "en", "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            return "admin/registerInstructor";
        }
        userRequestParams.setPassword(passwordEncoder.encode(userRequestParams.getPassword()));
        this.userService.registerInstructor(userRequestParams);
        return "redirect:/admin/registerInstructor?success";
    }

    @GetMapping("/admin/registerAdministrator")
    public String showAdminRegisterPage(@ModelAttribute("user") User user) {
        return "admin/registerAdministrator";
    }

    @PostMapping("/register/administrator")
    public String registerAdmin(@Valid @ModelAttribute("user") final UserRequestParams userRequestParams, BindingResult result) throws NotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(userRequestParams.getUsername());
        if (optionalUser.isPresent()) {
            result.rejectValue("username", "en", "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            return "admin/registerAdministrator";
        }
        userRequestParams.setPassword(passwordEncoder.encode(userRequestParams.getPassword()));
        this.userService.registerAdministrator(userRequestParams);
        return "redirect:/admin/registerAdministrator?success";
    }

    @PostMapping("/update/user/{userId}")
    public String updateUser(@Valid @ModelAttribute("user") UserRequestParams userRequestParams, @PathVariable("userId") final Long userId, BindingResult result, Model model) {
        this.userService.updateUser(userRequestParams, userId);
        return "redirect:/user/{userId}?success";
    }

    @GetMapping("/users/{roleId}")
    public String getStudents(@PathVariable("roleId") final Long roleId, Model model) {
        model.addAttribute("users", this.userService.findUsersByRole(roleId));
        return "admin/users";
    }

    @PostMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") final Long userId) {
        this.userService.deleteUserById(userId);
        return "home/homeNotSignedIn";
    }

    @PostMapping("/class/{userId}")
    public ResponseEntity<User> assignUserToClass(@PathVariable("userId") final Long userId, @RequestParam("classId") final Long classId) {
        User user = this.userService.assignUserToClass(userId, classId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable("userId") final Long userId, Model model) {
        Optional<User> user = this.userRepository.findById(userId);
        model.addAttribute("user", user.get());
        return "user/userDetail";
    }

}
