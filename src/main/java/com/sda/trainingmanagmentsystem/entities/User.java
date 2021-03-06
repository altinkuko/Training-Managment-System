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
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ParticipantApplication> participantApplication;
    @OneToMany(mappedBy = "instructor")
    @JsonIgnore
    private Set<Activities> modules;
    @ManyToOne
    @JoinColumn(name = "classesId")
    private Classes classes;

}
