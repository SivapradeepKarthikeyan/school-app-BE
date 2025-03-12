package com.school.school.excelMigrators.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.school.helpers.GeneralHelper;
import com.school.school.excelMigrators.dtos.StudentDto;
import com.school.school.excelMigrators.dtos.StudentIdpDto;
import com.school.school.entities.Student;
import com.school.school.excelMigrators.helpers.MigrationHelper;
import com.school.school.repositories.StudentRepository;
import com.school.school.responses.SchoolResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Optional;

import static com.school.school.constants.Constants.*;

@Service
@Transactional
public class StudentMigratorService {

    //Logger
    Logger logger = LoggerFactory.getLogger(StudentMigratorService.class);

    @Autowired
    StudentRepository studentRepository;

    public SchoolResponse migrateStudentsFromExcelToIDP(MultipartFile studentsExcelFile) {
        ArrayList<StudentIdpDto> failedStudents = new ArrayList<>();
        if (MigrationHelper.isStudentsExcelValid(studentsExcelFile)) {
            try {
                Workbook workbook = WorkbookFactory.create(studentsExcelFile.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    try {
                        if (row.getRowNum() != 0) {
                            StudentIdpDto student = new StudentIdpDto(
                                    row.getCell(0).getStringCellValue(),
                                    row.getCell(1).getStringCellValue(),
                                    row.getCell(2).getStringCellValue(),
                                    row.getCell(3).getStringCellValue());

                            if (!createStudentInIDP(student)) {
                                logger.warn("\uD83D\uDD34 IDP USER CREATION FAILED :: " + student.toString());
                                failedStudents.add(student);
                            }
                        }
                    }catch (Exception ignored){}
                }

            } catch (Exception e) {
                logger.warn(" \uD83D\uDFE1 ERROR IN PARSING STUDENTS EXCEL");
                return GeneralHelper.generateResponse(FAILED,STUDENT_MIGRATION_FAILED,500,null);
            }
            return GeneralHelper.generateResponse(SUCCESS, STUDENT_MIGRATION_SUCCESS, 200, failedStudents);
        }
        return GeneralHelper.generateResponse(FAILED, STUDENT_MIGRATION_FAILED, 400, null);
    }

    public boolean createStudentInIDP(StudentIdpDto student) {
        //Keycloak URL
        String keycloakUrl = "http://localhost:8080/realms/school/student-resources/api/v1/students/migration";
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(new ObjectMapper().writeValueAsString(student)))
                    .uri(URI.create(keycloakUrl))
                    .header("Content-Type", "application/json")
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SchoolResponse createStudentInDb(StudentDto studentDTO) {
        if (MigrationHelper.isStudentDTOValid(studentDTO)) {
            Optional<Student> optionalStudent = studentRepository.findById(studentDTO.getStudentId());
            if (optionalStudent.isEmpty()) {
                Student student = studentRepository.save(MigrationHelper.getStudentEntity(studentDTO));
                logger.info("\uD83D\uDFE2 STUDENT CREATED IN DB :: "+student.getStudentId() +" "+studentDTO.getStudentEmail());
                return GeneralHelper.generateResponse(SUCCESS, STUDENT_CREATION_SUCCESS, 200, null);
            }
            return GeneralHelper.generateResponse(CONFLICT, STUDENT_ALREADY_EXIST, 409, optionalStudent.get());
        }
        return GeneralHelper.generateResponse(FAILED, INVALID_DTO, 400, null);
    }

}
