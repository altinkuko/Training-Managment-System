package com.sda.trainingmanagmentsystem.repositories;

import com.sda.trainingmanagmentsystem.entities.ParticipantApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantApplicationRepository extends JpaRepository<ParticipantApplication,Long> {
    @Query(value = "select p from ParticipantApplication p where p.accepted = false")
    List<ParticipantApplication> readUnacceptedParticipantApplication();

}
