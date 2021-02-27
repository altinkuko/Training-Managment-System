package com.sda.trainingmanagmentsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<ParticipantApplication> participantApplication;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<GroupClasses> groups;
}
