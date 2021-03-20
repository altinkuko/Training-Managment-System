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
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
    public String registerTheUser(@Valid @ModelAttribute("user") UserRequestParams user, BindingResult result, Model model) {
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            result.rejectValue("username", "en", "There is already an account registered with that username");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerUser(user);
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

    @PostMapping("/user/{userId}")
    public String updateUser( @Valid UserRequestParams userRequestParams, @PathVariable("userId") final Long userId, BindingResult result, Model model) {
        model.addAttribute("user", this.userRepository.findById(userId).get());
        if (result.hasErrors()){
            return "redirect:/user/{userId}?";}
        this.userService.updateUser(userRequestParams, userId);
        return "redirect:/user/{userId}?success";
    }

    @GetMapping("/users/{roleId}")
    public ResponseEntity<List<User>> getStudents(@PathVariable("roleId") final Long roleId) {
        List<User> users = this.userService.findUsersByRole(roleId);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") final Long userId) {
        this.userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
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
