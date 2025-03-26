package com.school.school.excelMigrators.services;

import com.school.school.entities.Attendance;
import com.school.school.entities.Student;
import com.school.school.excelMigrators.helpers.MigrationHelper;
import com.school.school.repositories.AttendanceRepository;
import com.school.school.repositories.StudentRepository;
import com.school.school.helpers.GeneralHelper;
import com.school.school.responses.SchoolResponse;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import static com.school.school.constants.Constants.*;

@Service
@Transactional
public class AttendanceMigrationServices {
    //EXCEL FORMAT
    //email                date1 date2  date3  date4.......dateN
    // ...
    // ...
    //alpha@gmail.com       TRUE  TRUE   FALSE  TRUE ......TRUE

    //Logger
    Logger logger = LoggerFactory.getLogger(AttendanceMigrationServices.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AttendanceRepository attendanceRepository;

    public SchoolResponse markAttendance(String date, MultipartFile studentsAttendanceExcelFile) {
        if (MigrationHelper.isStudentsAttendanceExcelValid(studentsAttendanceExcelFile)) {
            try {
                // Getting first row (header)
                Workbook workbook = WorkbookFactory.create(studentsAttendanceExcelFile.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                Row headerRow = sheet.getRow(0);

                // Finding column index of the  date from the header
                int dateColumnIndex =getDateColumn(headerRow,date);

                if (dateColumnIndex == -1) {
                    logger.error("\uD83D\uDD3A Date column not found in the Excel file: " + date);
                    return GeneralHelper.generateResponse(FAILED, "Date column not found", 400, null);
                }

                //Iterating over rows and mark attendance
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) continue; // Skip header row

                    Cell emailCell = row.getCell(0);
                    String studentEmail = emailCell.getStringCellValue();
                    Optional<Student> studentOptional = studentRepository.findByStudentEmail(studentEmail);

                    if (studentOptional.isEmpty()) {
                        logger.warn("Student not found to mark attendance :: " + studentEmail);
                        continue;
                    }

                    Student student = studentOptional.get();

                    // Fetch attendance status from the found column.
                    Cell attendanceCell = row.getCell(dateColumnIndex);
                    boolean isPresent =attendanceCell.getBooleanCellValue();

                    Attendance attendance=new Attendance(student,date,isPresent);
                    attendanceRepository.save(attendance);

                    logger.info("✅ Student attendance marked :: " + studentEmail + " " + date + " " + isPresent);
                }

                return GeneralHelper.generateResponse(SUCCESS, STUDENT_ATTENDANCE_SUCCESS, 200, null);

            } catch (Exception e) {
                logger.warn(" \uD83D\uDFE1 ERROR IN PARSING STUDENTS ATTENDANCE EXCEL " + e.getMessage());
                return GeneralHelper.generateResponse(FAILED, STUDENT_ATTENDANCE_FAILED, 500, null);
            }
        }
        return GeneralHelper.generateResponse(FAILED, INVALID_DTO, 400, null);
    }

    private int getDateColumn(Row headerRow,String date){
        for (Cell cell : headerRow) {
            if(cell.getCellType()==CellType.STRING) continue; //Avoids the first cell
            if (cell.getCellType() == CellType.NUMERIC && date.equals(getDateAsString(cell.getDateCellValue()))) {
                return cell.getColumnIndex();
            }
        }
        return -1;
    }

    private String getDateAsString(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return outputFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

}
