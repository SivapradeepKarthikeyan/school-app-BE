package com.school.school.excelMigrators.contollers;


import com.school.school.excelMigrators.services.HomeWorkMigrationServices;
import com.school.school.responses.SchoolResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HomeWorkMigrationController {

    @Autowired
    HomeWorkMigrationServices homeWorkMigrationServices;

    @PostMapping("api/v1/admin/homeworks")
    public ResponseEntity<SchoolResponse> createHomeWork(@RequestParam String date,@RequestParam("file") MultipartFile homeWorksExcelFile){
       SchoolResponse schoolResponse= homeWorkMigrationServices.createHomeWork(date,homeWorksExcelFile);
       return ResponseEntity.status(schoolResponse.getStatusCode()).body(schoolResponse);
    }

}
