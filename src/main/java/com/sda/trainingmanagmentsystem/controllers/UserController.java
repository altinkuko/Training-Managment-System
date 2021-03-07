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
@RequestMapping("/user/")
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
    public ResponseEntity<User> assignUserToClass(@PathVariable("userId") final Long userId, @RequestParam("classId") final Long classId){
        User user = this.userService.assignUserToClass(userId,classId);
        return ResponseEntity.ok(user);
    }

}
