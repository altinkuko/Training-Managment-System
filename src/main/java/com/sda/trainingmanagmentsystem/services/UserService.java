package com.sda.trainingmanagmentsystem.services;

import com.sda.trainingmanagmentsystem.constants.ErrorMessages;
import com.sda.trainingmanagmentsystem.entities.Role;
import com.sda.trainingmanagmentsystem.entities.User;
import com.sda.trainingmanagmentsystem.models.errors.NotFoundException;
import com.sda.trainingmanagmentsystem.models.pojo.UserRequestParams;
import com.sda.trainingmanagmentsystem.repositories.ClassesRepository;
import com.sda.trainingmanagmentsystem.repositories.RoleRepository;
import com.sda.trainingmanagmentsystem.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ClassesRepository classesRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        return userRepository.save(user);
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
        if (isRegister(userRequestParams.getUsername()) == false) {
            UserService.log.info("User saved successfully");
            return this.userRepository.save(user);
        } else return user;
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
        if (isRegister(userRequestParams.getUsername()) == false) {
            UserService.log.info("User saved successfully");
            return this.userRepository.save(user);
        } else return user;
    }

    public User updateUser(final UserRequestParams userRequestParams, final Long userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setFirstName(userRequestParams.getFirstName());
        user.setLastName(userRequestParams.getLastName());
        user.setEmail(userRequestParams.getEmail());
        user.setUsername(userRequestParams.getUsername());
        user.setPassword(this.passwordEncoder.encode(userRequestParams.getPassword()));
        return userRepository.save(user);
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

    public List<User> findUsersByRole(final Long roleId) {
        List<User> usersByRole = this.userRepository.findUsersByRole(roleId);
        return usersByRole;
    }

    public void deleteUserById(final Long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        this.userRepository.delete(user);
    }

    public boolean isRegister(final String username) {

        for (User user : this.userRepository.findAll()
        ) {
            if (user.getUsername().equals(username)) return true;
        }
        return false;
    }

    public User assignUserToClass(final Long userId, final Long classId){
        User user = this.userRepository.findById(userId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION));
        user.setClasses(this.classesRepository.findById(classId).orElseThrow(()-> new NotFoundException(ErrorMessages.ID_NOT_FOUND_EXCEPTION)));
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("user not found");
        else
        return user.get();
    }
}
