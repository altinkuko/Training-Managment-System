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

    @PostMapping("/register/instructor")
    public ResponseEntity<User> postInstructor(@RequestBody @Valid final UserRequestParams userRequestParams) throws NotFoundException {
        User user = this.userService.registerInstructor(userRequestParams);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<User> postAdmin(@RequestBody @Valid final UserRequestParams userRequestParams) throws NotFoundException {
        User user = this.userService.registerAdministrator(userRequestParams);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update/user/{userId}")
    public String updateUser( @Valid @ModelAttribute("user") UserRequestParams userRequestParams, @PathVariable("userId") final Long userId, BindingResult result, Model model) {
        this.userService.updateUser(userRequestParams, userId);
        return "redirect:/user/{userId}?success";
    }

    @GetMapping("/users/{roleId}")
    public ResponseEntity<List<User>> getStudents(@PathVariable("roleId") final Long roleId) {
        List<User> users = this.userService.findUsersByRole(roleId);
        return ResponseEntity.ok(users);
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
    public String getUser(@PathVariable("userId") final Long userId, Model model){
        Optional<User> user = this.userRepository.findById(userId);
        model.addAttribute("user", user.get());
        return "user/userDetail";
    }

}
