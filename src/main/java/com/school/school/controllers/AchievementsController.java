package com.school.school.controllers;

import com.school.school.responses.SchoolResponse;
import com.school.school.services.AchievementsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<SchoolResponse> createAchievement(@RequestParam String studentId, @RequestParam String date, @RequestParam("file") MultipartFile file) {
        SchoolResponse response = achievementsServices.createAchievement(studentId, date, file);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
