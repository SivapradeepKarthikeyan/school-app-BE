package com.school.school.controllers;

import com.school.school.responses.SchoolResponse;
import com.school.school.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @Autowired
    StudentServices studentServices;

    //This will be called by student to get their profile
    @GetMapping("api/v1/student/{id}")
    public ResponseEntity<SchoolResponse> getStudentByEmail(@PathVariable String id){
        SchoolResponse response=studentServices.getStudentById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
