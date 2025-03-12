package com.school.school.services;


import com.school.school.entities.HomeWork;
import com.school.school.excelMigrators.services.HomeWorkMigrationServices;
import com.school.school.helpers.GeneralHelper;
import com.school.school.repositories.HomeWorkRepository;
import com.school.school.responses.SchoolResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.school.school.constants.Constants.*;
import static com.school.school.constants.Constants.HOME_WORK_FETCH_FAIL;

@Service
public class HomeWorkServices {

    Logger logger = LoggerFactory.getLogger(HomeWorkServices.class);

    @Autowired
    HomeWorkRepository homeWorkRepository;

    public SchoolResponse getHomeWorkByDateAndClassAndSchool(String date, String className, String school) {
        List<HomeWork> homeWork = homeWorkRepository.findByDateAndClassNameAndSchool(date, className, school);
        try {
            logger.info("\uD83D\uDCD8 fetching home work :: "+date+" "+className+" "+school);
            return GeneralHelper.generateResponse(SUCCESS, HOME_WORK_FETCH_SUCCESS, 200, null);
        }catch (Exception e){
            logger.info("\uD83D\uDD3A exception in fetching homework :: "+date+" "+className+" "+school);
            return GeneralHelper.generateResponse(FAILED, HOME_WORK_FETCH_FAIL, 500, null);
        }
    }
}
