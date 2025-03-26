package com.school.school.services;

import com.school.school.constants.Constants;
import com.school.school.entities.Achievements;
import com.school.school.entities.Student;
import com.school.school.excelMigrators.services.AttendanceMigrationServices;
import com.school.school.helpers.GeneralHelper;
import com.school.school.repositories.AchievementsRepository;
import com.school.school.repositories.StudentRepository;
import com.school.school.responses.SchoolResponse;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.school.school.constants.Constants.*;

@Service
@Transactional
public class AchievementsServices {

    //Logger
    Logger logger = LoggerFactory.getLogger(AchievementsServices.class);

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AchievementsRepository achievementsRepository;


    public SchoolResponse getAchievements(String studentId){
        if(studentRepository.findById(studentId).isPresent()){
            List<Achievements> achievementsList=achievementsRepository.findByStudentId(studentId);
            return GeneralHelper.generateResponse(SUCCESS,ACHIEVEMENTS_FETCH_SUCCESS,200,achievementsList);
        }
        return GeneralHelper.generateResponse(SUCCESS,ACHIEVEMENTS_FETCH_SUCCESS,404,null);
    }

    public SchoolResponse createAchievement(String studentId, String date, MultipartFile file) {
        if(GeneralHelper.checkAchievementType(file)){
            Optional<Student> studentOptional=studentRepository.findById(studentId);
            if(studentOptional.isPresent()) {
                Achievements achievements=new Achievements(studentOptional.get(),date,"Test title",storeFileInCloud(date,file));
                achievementsRepository.save(achievements);
                return GeneralHelper.generateResponse(SUCCESS,ACHIEVEMENTS_SUCCESS,200,achievements);
            }else {
                return GeneralHelper.generateResponse(FAILED,ACHIEVEMENTS_FAILED,404,null);
            }
        }
        return GeneralHelper.generateResponse(FAILED,ACHIEVEMENTS_FAILED,400,null);
    }

    private String storeFileInCloud(String date,MultipartFile file){
       try {
           return "https://www.qwertyuiopasdfghjklzxcvbnm,";
       }catch (Exception e){
           logger.error("Error in uploading file to cloud :: "+e.getMessage());
           return " ";
       }
    }
}
