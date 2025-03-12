package com.school.school.services;

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
import java.util.Map;
import java.util.Optional;

import static com.school.school.constants.Constants.*;

@Service
@Transactional
public class AchievementsServices {

    //Logger
    Logger logger = LoggerFactory.getLogger(AchievementsServices.class);

    @Autowired
    AchievementsRepository achievementsRepository;

    @Autowired
    StudentRepository studentRepository;

    public SchoolResponse createAchievement(String studentId, String date, MultipartFile file) {
        Optional<Achievements> optionalAchievements = achievementsRepository.findById(studentId);
        try {
            if (optionalAchievements.isPresent()) {
                Achievements achievements = optionalAchievements.get();
                //TODO :: Call cloud storage and save the user file and get the link.
                String link = storeFileInCloud(date,file);
                achievements.getAchievementsTrack().put(date, "");
                return GeneralHelper.generateResponse(SUCCESS, ACHIEVEMENTS_SUCCESS, 200, link);
            } else {
                Optional<Student> optionalStudent = studentRepository.findById(studentId);
                if (optionalStudent.isEmpty())
                    return GeneralHelper.generateResponse(FAILED, ACHIEVEMENTS_FAILED, 404, null);

                Student student = optionalStudent.get();
                Achievements achievements=student.getStudentAchievements();
                if(achievements==null){
                    achievements=new Achievements(student,new HashMap<>());
                    student.setStudentAchievements(achievements);
                }

                String link = storeFileInCloud(date,file);
                achievements.getAchievementsTrack().put(date,link);

                return GeneralHelper.generateResponse(SUCCESS, ACHIEVEMENTS_SUCCESS, 200, link);
            }
        } catch (Exception e) {
            logger.warn("\uD83E\uDDE8 "+e.getMessage());
            return GeneralHelper.generateResponse(FAILED, ACHIEVEMENTS_FAILED, 500, e.getMessage());
        }
    }

    private String storeFileInCloud(String date,MultipartFile file){
        return "https://www.qwertyuiopasdfghjklzxcvbnm,";
    }
}
