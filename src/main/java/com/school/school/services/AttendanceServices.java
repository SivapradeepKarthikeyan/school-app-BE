package com.school.school.services;

import com.school.school.entities.Attendance;
import com.school.school.entities.Student;
import com.school.school.repositories.AttendanceRepository;
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
public class AttendanceServices {

    //Logger
    Logger logger = LoggerFactory.getLogger(AttendanceServices.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AttendanceRepository attendanceRepository;

    public SchoolResponse getStudentAttendance(String email) {

        Optional<Student> optionalStudent = studentRepository.findByStudentEmail(email);
        if (optionalStudent.isPresent()) {
            List<Attendance> attendanceList = attendanceRepository.findByStudentId(optionalStudent.get().getStudentId());
            return new SchoolResponse(SUCCESS, STUDENT_ATTENDANCE_FETCH_SUCCESS, 200, attendanceList );
        }
        return new SchoolResponse(FAILED, STUDENT_ATTENDANCE_FETCH_FAILED, 404, null);
    }

}
