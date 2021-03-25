package com.sda.trainingmanagmentsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classesId;
    @NotNull
    @NotEmpty(message = "Please enter a name")
    private String className;
    private Boolean active;
    private byte[] file;
    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<Activities> activities;
    @ManyToOne
    @JoinColumn(name = "courseId")
    @JsonIgnore
    private Course course;
    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<User> students;
}
