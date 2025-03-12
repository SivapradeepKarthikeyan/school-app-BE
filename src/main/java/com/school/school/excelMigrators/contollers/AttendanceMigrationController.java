package com.school.school.excelMigrators.contollers;

import com.school.school.excelMigrators.services.AttendanceMigrationServices;
import com.school.school.responses.SchoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class AttendanceMigrationController {

    @Autowired
    AttendanceMigrationServices attendanceMigrationServices;

    //NOTE :: NEED TO BE A .xlsx format
    @PutMapping("api/v1/admin/attendance")
    public ResponseEntity<SchoolResponse> updateStudentsAttendance(@RequestParam String date,@RequestParam("file") MultipartFile studentsExcelFile) throws IOException {
        SchoolResponse response=attendanceMigrationServices.markAttendance(date,studentsExcelFile);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
