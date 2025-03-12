package com.school.school.excelMigrators.services;

import com.school.school.entities.HomeWork;
import com.school.school.excelMigrators.helpers.MigrationHelper;
import com.school.school.repositories.HomeWorkRepository;
import com.school.school.helpers.GeneralHelper;
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

import java.text.SimpleDateFormat;
import java.util.*;

import static com.school.school.constants.Constants.*;

@Service
@Transactional
public class HomeWorkMigrationServices {
    //EXCEL FORMAT
    // date class school sub1 sub2 sub3 ....subN
    // ...
    // ...
    //05-05-2025

    Logger logger = LoggerFactory.getLogger(HomeWorkMigrationServices.class);

    @Autowired
    HomeWorkRepository homeWorkRepository;

    //If homework is not present it creates a new homework else updates the existing one.
    public SchoolResponse createHomeWork(String date, MultipartFile homeWorkExcel) {
        if (MigrationHelper.isHomeWorkExcelValid(homeWorkExcel)) {
            try {
                Workbook workbook = WorkbookFactory.create(homeWorkExcel.getInputStream());
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    if (row.getRowNum() != 0) {
                        //Getting the date row from excl that is from query param
                        if (getDateAsString(row.getCell(0).getDateCellValue()).equals(date)) {
                            //Check if already homework exist
                            String class_=getClass(row);
                            String school=getSchool(row);
                            List<HomeWork> homeWorkList=homeWorkRepository.findByDateAndClassNameAndSchool(date,class_,school);
                            if(homeWorkList.isEmpty()) {
                                HashMap<String, String> homeWorks = createHomeWorksMap(sheet,row);
                                HomeWork homeWork = new HomeWork();
                                homeWork.setId(UUID.randomUUID().toString());
                                homeWork.setDate(date);
                                homeWork.setClassName(class_);
                                homeWork.setSchool(school);
                                homeWork.setHomeWorks(homeWorks);

                                logger.info("\uD83D\uDCD7 saving new home work :: " + date + " " + class_ + " " + school + " " + homeWork);

                                homeWorkRepository.save(homeWork);
                            }else{
                                logger.info("\uD83D\uDCD2 updating existing home work :: " + date + " " + class_ + " " + school + " ");
                                 HomeWork homeWork=homeWorkList.get(0);
                                 HashMap<String, String> newHomeWorks = createHomeWorksMap(sheet,row);
                                 homeWork.setHomeWorks(newHomeWorks);
                            }
                        }
                    }
                }
                return GeneralHelper.generateResponse(SUCCESS, HOMEWORK_SUCCESS, 200, null);

            } catch (Exception e) {
                logger.info("\uD83D\uDD3A exception in saving home work :: " + e.getMessage());
                return GeneralHelper.generateResponse(FAILED, HOMEWORK_FAIL, 500, null);
            }
        }
        return GeneralHelper.generateResponse(FAILED, HOMEWORK_FAIL, 400, null);
    }


    private String getDateAsString(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return outputFormat.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    private String getClass(Row row){
        return row.getCell(1).getStringCellValue();
    }

    private String getSchool(Row row){
        return row.getCell(2).getStringCellValue();
    }

    private String getSubject(Sheet sheet, int cell) {
        try {
            return sheet.getRow(0).getCell(cell).getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }

    private String getHomeWork(Row row, int cell) {
        try {
            return row.getCell(cell).getStringCellValue();
        } catch (Exception e) {
            return "";
        }
    }

    private HashMap<String,String> createHomeWorksMap(Sheet sheet,Row row){
        HashMap<String,String> homeWorksMap=new HashMap<>();
        for (int i = 3; i <= 8; i++) {
            String subject = getSubject(sheet, i);
            String homework = getHomeWork(row, i);
            if (homework.length() == 0) continue;
            homeWorksMap.put(subject, homework);
        }
        return homeWorksMap;
    }
}
