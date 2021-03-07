package com.sda.trainingmanagmentsystem.controllers;

import com.sda.trainingmanagmentsystem.entities.Activities;
import com.sda.trainingmanagmentsystem.models.pojo.ActivitiesRequestParams;
import com.sda.trainingmanagmentsystem.services.ActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/activities/")
public class ActivitiesController {
    @Autowired
    private ActivitiesService activitiesService;

    @GetMapping("/module/{date}")
    public ResponseEntity<List<Activities>> listActivitiesByDate(@PathVariable("date") LocalDate date) {
        List<Activities> activities = this.activitiesService.findActivitiesByDate(date);
        return ResponseEntity.ok(activities);
    }

    @GetMapping("/modules/{gcId}")
    public ResponseEntity<List<Activities>> listActivitiesByGroupClasses(@PathVariable("gcId") final Long classId) {
        List<Activities> activities = this.activitiesService.listActivitiesByClasses(classId);
        return ResponseEntity.ok(activities);
    }

    @PostMapping("/create")
    public ResponseEntity<Activities> createActivities (@RequestBody final ActivitiesRequestParams activitiesRequestParams){
        Activities activities = this.activitiesService.createActivities(activitiesRequestParams);
        return ResponseEntity.ok(activities);
    }
    @DeleteMapping("/delete/{activitiesId}")
    public ResponseEntity<Void> deleteActivities(@PathVariable("activitiesId") final Long activitiesId){
        this.activitiesService.deleteActivities(activitiesId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/update/{activitiesId}")
    public ResponseEntity<Activities> updateActivities(@RequestBody final ActivitiesRequestParams activitiesRequestParams, @PathVariable("activitiesId") final Long activitiesId){
        Activities activities = this.activitiesService.updateActivities(activitiesRequestParams, activitiesId);
        return ResponseEntity.ok(activities);
    }
}
