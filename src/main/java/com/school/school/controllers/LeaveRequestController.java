package com.school.school.controllers;


import com.school.school.dtos.LeaveRequestDTO;
import com.school.school.entities.LeaveRequest;
import com.school.school.responses.SchoolResponse;
import com.school.school.services.HomeWorkServices;
import com.school.school.services.LeaveRequestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LeaveRequestController {

    @Autowired
    LeaveRequestServices leaveRequestServices;

    //This will be called by student to request a leave.
    @PostMapping("api/v1/student/leave-request/{email}")
    public ResponseEntity<SchoolResponse> postLeaveRequest(@PathVariable String email,@RequestBody LeaveRequestDTO leaveRequestDTO) {
        SchoolResponse response = leaveRequestServices.postLeaveRequest(email, leaveRequestDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    //This will be called by student to get leave request details.
    @GetMapping("api/v1/student/leave-request/{studentId}")
    public ResponseEntity<SchoolResponse> getLeaveRequests(@PathVariable String studentId) {
        SchoolResponse response = leaveRequestServices.getLeaveRequests(studentId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
