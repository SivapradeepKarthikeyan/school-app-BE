package com.school.school.dtos;

public class LeaveRequestDTO {

    public LeaveRequestDTO() {}

    public LeaveRequestDTO(String date, String reason, String classTeacherEmail) {
        this.date = date;
        this.reason = reason;
        this.classTeacherEmail = classTeacherEmail;
    }

    private String date;
    private String reason;
    private String classTeacherEmail;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getClassTeacherEmail() {
        return classTeacherEmail;
    }

    public void setClassTeacherEmail(String classTeacherEmail) {
        this.classTeacherEmail = classTeacherEmail;
    }

    @Override
    public String toString() {
        return "LeaveRequestDTO{" +
                "date='" + date + '\'' +
                ", reason='" + reason + '\'' +
                ", classTeacherEmail='" + classTeacherEmail + '\'' +
                '}';
    }
}
