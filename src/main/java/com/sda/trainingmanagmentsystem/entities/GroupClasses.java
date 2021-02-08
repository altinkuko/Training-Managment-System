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
public class GroupClasses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupClassesId;
    private String groupName;
    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private Set<Classes> classes;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
}
