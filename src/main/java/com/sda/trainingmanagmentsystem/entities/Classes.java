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
    @ManyToOne
    @JoinColumn(name = "groupClassesId")
    private GroupClasses group;
    @OneToMany(mappedBy = "classesNotification")
    @JsonIgnore
    private Set<Notification> notifications;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User instructor;
    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private Set<Activities> activities;
}
