package com.sda.trainingmanagmentsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    @NotEmpty
    @Size(min = 3, message = "Please enter a Course Name")
    private String courseName;
    @NotEmpty(message = "Please enter e Description")
    private String courseDescription;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<ParticipantApplication> participantApplication;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Classes> groups;
}
