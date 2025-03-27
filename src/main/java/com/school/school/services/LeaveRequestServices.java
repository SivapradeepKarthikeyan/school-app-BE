package com.school.school.services;


import com.school.school.dtos.LeaveRequestDTO;
import com.school.school.entities.LeaveRequest;
import com.school.school.entities.Student;
import com.school.school.helpers.GeneralHelper;
import com.school.school.repositories.LeaveRequestRepository;
import com.school.school.repositories.StudentRepository;
import com.school.school.responses.SchoolResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.school.school.constants.Constants.*;

@Service
@Transactional
public class LeaveRequestServices {

    Logger logger = LoggerFactory.getLogger(LeaveRequestServices.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    LeaveRequestRepository leaveRequestRepository;


    public SchoolResponse getLeaveRequests(String studentId){
        if(studentRepository.findById(studentId).isPresent()){
            List<LeaveRequest> leaveRequests =leaveRequestRepository.findByStudentId(studentId);
            return GeneralHelper.generateResponse(SUCCESS,LEAVE_REQUEST_FETCH_SUCCESS,200,leaveRequests);
        }
        return GeneralHelper.generateResponse(FAILED,LEAVE_REQUEST_FETCH_FAILED,404,null);
    }


    public SchoolResponse postLeaveRequest(String email, LeaveRequestDTO leaveRequestDTO){
        if(GeneralHelper.checkLeaveRequestDTO(leaveRequestDTO)){
            Optional<Student> optionalStudent =studentRepository.findByStudentEmail(email);
            if(optionalStudent.isPresent()){
                LeaveRequest leaveRequest=new LeaveRequest(optionalStudent.get(),leaveRequestDTO.getDate(),leaveRequestDTO.getReason(), leaveRequestDTO.getClassTeacherEmail(), null);
                leaveRequestRepository.save(leaveRequest);
                return GeneralHelper.generateResponse(SUCCESS,LEAVE_REQUEST_SUCCESS,200,leaveRequest);
            }
            return GeneralHelper.generateResponse(FAILED,LEAVE_REQUEST_FAILED,404,null);
        }
        return GeneralHelper.generateResponse(FAILED,LEAVE_REQUEST_FAILED,400,null);
    }

}
