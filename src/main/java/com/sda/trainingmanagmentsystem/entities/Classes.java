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
public class Classes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classesId;
    private String className;
    private Boolean active;
    private byte[] file;
    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<Activities> activities;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<User> students;
    @OneToMany(mappedBy = "classesNotification")
    @JsonIgnore
    private Set<Notification> notifications;
}
