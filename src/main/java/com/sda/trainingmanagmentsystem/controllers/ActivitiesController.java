package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.services.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ActivitiesController {
    @Autowired
    private ActivitiesService activitiesService;

    @GetMapping("/module/{date}")
    public ResponseEntity<List<Activities>> listActivitiesByDate(@PathVariable("date") LocalDate date){
        List<Activities> activities = this.activitiesService.findActivitiesByDate(date);
        return ResponseEntity.ok(activities);
    }
    @GetMapping("/modules/{gcId}")
    public ResponseEntity<List<Activities>> listActivitiesByGroupClasses(@PathVariable("gcId") final Long groupClassesId){
        List<Activities> activities = this.activitiesService.listActivitiesByGroupClasses(groupClassesId);
        return ResponseEntity.ok(activities);
    }
}
