package com.school.school.services;

import com.school.school.entities.Student;
import com.school.school.helpers.GeneralHelper;
import com.school.school.repositories.StudentRepository;
import com.school.school.responses.SchoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.school.school.constants.Constants.*;
import static com.school.school.constants.Constants.STUDENT_FETCH_FAILED;


@Service
public class StudentServices {
    @Autowired
    StudentRepository studentRepository;

    public SchoolResponse getStudentById(String id){
        try {
            Optional<Student> studentOptional= studentRepository.findById(id);
            return GeneralHelper.generateResponse(SUCCESS, STUDENT_FETCH_SUCCESS, 200, studentOptional.get());
        }catch (Exception e){
            return GeneralHelper.generateResponse(FAILED, STUDENT_FETCH_FAILED, 500, null);
        }
    }
}
