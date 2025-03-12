package com.school.school.controllers;

import com.school.school.responses.SchoolResponse;
import com.school.school.services.HomeWorkServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeWorkController {

    @Autowired
    HomeWorkServices homeWorkServices;

    //This will be called by student to get their homeworks
    @GetMapping("api/v1/student/homeworks")
    public ResponseEntity<SchoolResponse> getHomeWorks(@RequestParam String date, String className, String school){
        SchoolResponse schoolResponse= homeWorkServices.getHomeWorkByDateAndClassAndSchool(date,className,school);
        return ResponseEntity.status(schoolResponse.getStatusCode()).body(schoolResponse);
    }

}
