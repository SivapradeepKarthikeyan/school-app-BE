package com.school.school.excelMigrators.contollers;

import com.school.school.excelMigrators.dtos.StudentDto;
import com.school.school.excelMigrators.services.StudentMigratorService;
import com.school.school.responses.SchoolResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class StudentMigratorController {

    @Autowired
    StudentMigratorService studentMigratorService;

    //NOTE :: NEED TO BE A .xlsx format
    @PostMapping("api/v1/admin/student/migration")
    public ResponseEntity<SchoolResponse> migrateStudentsFromExcelToIDP(@RequestParam("file") MultipartFile studentsExcelFile) throws IOException {
        SchoolResponse response=studentMigratorService.migrateStudentsFromExcelToIDP(studentsExcelFile);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    //This will be called by IDP to create users in app DB
    @PostMapping("api/v1/admin/student")
    public ResponseEntity<SchoolResponse> createStudentInDb(@RequestBody StudentDto studentDto){
       SchoolResponse response= studentMigratorService.createStudentInDb(studentDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
