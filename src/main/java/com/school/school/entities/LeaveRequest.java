package com.school.school.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    public LeaveRequest() {}

    public LeaveRequest(Student student,String studentId, String leaveId, String leaveDate, String leaveReason, String teacherEmail) {
        this.studentId = studentId;
        this.leaveId = leaveId;
        this.leaveDate = leaveDate;
        this.leaveReason = leaveReason;
        this.teacherEmail = teacherEmail;
    }


    private String studentId;
    @Id
    private String leaveId;
    private String leaveDate;
    private String leaveReason;
    private String teacherEmail;



    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
}
