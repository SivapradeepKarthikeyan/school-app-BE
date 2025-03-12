package com.school.school.helpers;

import com.school.school.responses.SchoolResponse;

public class GeneralHelper {
    public static SchoolResponse generateResponse(String status,String message,int statusCode,Object data){
        return new SchoolResponse(status,message,statusCode,data);
    }
}
