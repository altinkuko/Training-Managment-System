package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Role;
import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.UserRequestParams;
import com.sda.trainingmanagmentsystem.repositories.RoleRepository;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User registerUser(final UserRequestParams userRequestParams) {
        userRequestParams.validPassword();
        User user = new User();
        Role role = roleRepository.findById(3L).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        user.setRole(role);
        UserService.log.info("User saved successfully");
        return this.userRepository.save(user);
    }

    public User registerInstructor(final UserRequestParams userRequestParams) {
        userRequestParams.validPassword();
        User user = new User();
        Role role = roleRepository.findById(2L).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        user.setRole(role);
        UserService.log.info("User saved successfully");
        return this.userRepository.save(user);
    }

    public User registerAdministrator(final UserRequestParams userRequestParams) {
        userRequestParams.validPassword();
        User user = new User();
        Role role = roleRepository.findById(1L).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        user.setRole(role);
        UserService.log.info("User saved successfully");
        return this.userRepository.save(user);
    }

    public User updateUser(final UserRequestParams userRequestParams, final Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        return user;
    }

    public User updateInstructor(final UserRequestParams userRequestParams, final Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        return user;
    }

    public User updateAdmin(final UserRequestParams userRequestParams, final Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(userRequestParams.getPassword());
        return user;
    }

    public List<User> findStudents(final String role) {
        List<User> students = this.userRepository.findStudents(role);
        return students;
    }

    public void deleteUserById(final Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        this.userRepository.delete(user);
    }

}
