package com.school.school.excelMigrators.helpers;

import com.school.school.excelMigrators.dtos.StudentDto;
import com.school.school.entities.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class MigrationHelper {

    public static boolean isStudentsExcelValid(MultipartFile file) {
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Row row = workbook.getSheetAt(0).getRow(0);
            if (!row.getCell(0).getStringCellValue().equals("studentName"))
                return false;
            if (!row.getCell(1).getStringCellValue().equals("studentEmail"))
                return false;
            if (!row.getCell(2).getStringCellValue().equals("studentClass"))
                return false;
            if (!row.getCell(3).getStringCellValue().equals("studentSchool"))
                return false;
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean isStudentDTOValid(StudentDto studentDTO) {
        if (studentDTO.getStudentId() == null || studentDTO.getStudentId().length() == 0) return false;
        if (studentDTO.getStudentName() == null || studentDTO.getStudentName().length() == 0) return false;
        if (studentDTO.getStudentEmail() == null || studentDTO.getStudentEmail().length() == 0) return false;
        if (studentDTO.getStudentClass() == null || studentDTO.getStudentClass().length() == 0) return false;
        if (studentDTO.getStudentSchool() == null || studentDTO.getStudentSchool().length() == 0) return false;
        return true;
    }

    public static Student getStudentEntity(StudentDto studentDTO) {
        return new Student(studentDTO.getStudentId(),
                studentDTO.getStudentName(),
                studentDTO.getStudentEmail(),
                studentDTO.getStudentClass(),
                studentDTO.getStudentSchool());
    }

    public static boolean isStudentsAttendanceExcelValid(MultipartFile file){
        try {
//            Workbook workbook = WorkbookFactory.create(file.getInputStream());
////            //Excel name validation
////            // ex :: 8A-JAYCEES
////            String excelName=workbook.getSheetName(0);
////            if(!excelName.matches("^\\d+[A-Z]-[A-Z]+$\n"))
////                return false;
//
//            Row row = workbook.getSheetAt(0).getRow(0);
//            if (!row.getCell(0).getStringCellValue().equals("studentName"))
//                return false;
//            //DD-MM-YY
//            if (row.getCell(row.getLastCellNum()).getStringCellValue().matches("\\d{2}/\\d{2}/\\d{2}")) {
//                return false;
//            }
            return true;
        } catch (Exception e) {
            return false;
        }
        //return true;
    }

    public static String getStudentAttendanceExcelName(MultipartFile file){
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            return  workbook.getSheetName(0);
        } catch (IOException e) {
            return "";
        }
    }

    public static boolean isHomeWorkExcelValid(MultipartFile file){
        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Row row = workbook.getSheetAt(0).getRow(0);
            if(!row.getCell(0).getStringCellValue().equals("date"))
                return false;
            if(!row.getCell(1).getStringCellValue().equals("class"))
                return false;
            if(!row.getCell(2).getStringCellValue().equals("school"))
                return false;
            return true;
        } catch (Exception e) {
           return false;
        }
    }

}
