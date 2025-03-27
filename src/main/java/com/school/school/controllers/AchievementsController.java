package com.school.school.controllers;

import com.school.school.responses.SchoolResponse;
import com.school.school.services.AchievementsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//WHY ? ACHIEVEMENT CONTROLLER IS SEPARATE API AND NOT WITH STUDENT API
//REASON : IF IT IS USED INSIDE STUDENT CONTROLLER,
//EVERYTIME WE NEED TO FETCH STUDENT BY ID (SO ALL QUERIES INSIDE WILL BE EXECUTED FOR ATTENDANCE,LEAVE REQUEST WILL ALSO BE EXECUTED WHICH TAKES MORE TIME.)
//IF WE WRITE HERE ONLY ACHIEVEMENTS QUERY IS EXECUTED WHICH CONSUMES LESS TIME.


@RestController
public class AchievementsController {

    @Autowired
    AchievementsServices achievementsServices;

    @PostMapping("api/v1/student/achievement")
    public ResponseEntity<SchoolResponse> createAchievement(@RequestParam String email, @RequestParam String date, @RequestParam("file") MultipartFile file) {
        SchoolResponse response = achievementsServices.createAchievement(email, date, file);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("api/v1/student/achievement/{email}")
    public ResponseEntity<SchoolResponse> getAchievements(@PathVariable String email) {
        SchoolResponse response = achievementsServices.getAchievements(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
