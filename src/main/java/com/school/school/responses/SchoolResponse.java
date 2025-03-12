package com.school.school.responses;

public class SchoolResponse {

    public SchoolResponse(String status, String message, int statusCode, Object data) {
        this.status = status;
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
    }

    private String status;
    private String message;
    private int statusCode;
    Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
