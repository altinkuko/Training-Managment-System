package com.sda.trainingmanagmentsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Activities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activitiesId;
    private String subject;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "classId")
    @JsonIgnore
    private Classes classes;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User instructor;
}
