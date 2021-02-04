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
    private Long id;
    private String courseName;
    @OneToOne(mappedBy = "course")
    @JsonIgnore
    private ApplicationForm applicationForm;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Classes> classes;
    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Activities> activities;
}
