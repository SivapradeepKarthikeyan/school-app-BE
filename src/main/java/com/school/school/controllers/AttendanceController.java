package com.school.school.controllers;


import com.school.school.responses.SchoolResponse;
import com.school.school.services.AttendanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttendanceController {

    @Autowired
    AttendanceServices attendanceServices;

    @GetMapping("api/v1/student/attendance/{email}")
    public ResponseEntity<SchoolResponse> getStudentAttendance(@PathVariable String email){
        SchoolResponse response=attendanceServices.getStudentAttendance(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
