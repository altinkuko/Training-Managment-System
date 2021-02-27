package com.sda.trainingmanagmentsystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantApplicationId;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "courseId")
    private Course course;
    private Boolean accepted;

}
