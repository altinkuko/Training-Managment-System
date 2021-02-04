package com.sda.trainingmanagmentsystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @OneToOne
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToOne(mappedBy = "user")
    private ApplicationForm applicationForm;
    @OneToOne(mappedBy = "user")
    private Classes classes;
    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private Set<Notification> notifications;


}
