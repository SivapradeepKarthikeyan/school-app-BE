package com.school.school.helpers;

import com.school.school.dtos.LeaveRequestDTO;
import com.school.school.responses.SchoolResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class GeneralHelper {
    public static SchoolResponse generateResponse(String status,String message,int statusCode,Object data){
        return new SchoolResponse(status,message,statusCode,data);
    }

    public static boolean checkAchievementType(MultipartFile file){
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        return extension.equals("pdf") || extension.equals("jpeg") || extension.equals("mp4") || extension.equals("mp3");
    }

    public static boolean checkLeaveRequestDTO(LeaveRequestDTO leaveRequestDTO){
        if(leaveRequestDTO.getDate()==null || leaveRequestDTO.getDate().length()==0) return false;
        if (leaveRequestDTO.getReason()==null || leaveRequestDTO.getReason().length()==0) return false;
        if (leaveRequestDTO.getClassTeacherEmail()==null || leaveRequestDTO.getClassTeacherEmail().length()==0) return false;
        return true;
    }

}
