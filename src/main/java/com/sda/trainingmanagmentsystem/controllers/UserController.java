package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.UserRequestParams;
import com.sda.trainingmanagmentsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register/user")
    public ResponseEntity<User> postUser(@RequestBody @Valid final UserRequestParams userRequestParams) throws NotFoundException {
        User user = this.userService.registerUser(userRequestParams);
        return ResponseEntity.ok(user);
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
    public ResponseEntity<User> updateUser(@RequestBody @Valid final UserRequestParams userRequestParams, @PathVariable("userId") final Long userId) {
        User user = this.userService.updateUser(userRequestParams, userId);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/update/instructor/{userId}")
    public ResponseEntity<User> updateInstructor(@RequestBody @Valid final UserRequestParams userRequestParams, @PathVariable("userId") final Long userId) {
        User instructor = this.userService.updateInstructor(userRequestParams, userId);
        return ResponseEntity.ok(instructor);
    }

    @PostMapping("/update/admin/{userId}")
    public ResponseEntity<User> updateAdmin(@RequestBody @Valid final UserRequestParams userRequestParams, @PathVariable("userId") final Long userId) {
        User admin = this.userService.updateAdmin(userRequestParams, userId);
        return ResponseEntity.ok(admin);
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<List<User>> getStudents(@PathVariable("role") final String role) {
        List<User> students = this.userService.findStudents(role);
        return ResponseEntity.ok(students);
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") final Long userId) {
        this.userService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }

}
