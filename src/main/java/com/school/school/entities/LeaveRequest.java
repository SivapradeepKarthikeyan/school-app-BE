package com.school.school.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "leave_requests")
public class LeaveRequest {

    public LeaveRequest() {}

    public LeaveRequest(Student student, String studentLeaveRequestDate, String studentLeaveRequestDateReason, String studentClassTeacherEmail, String studentClassTeacherResponse) {
        this.student = student;
        this.studentLeaveRequestDate = studentLeaveRequestDate;
        this.studentLeaveRequestReason = studentLeaveRequestDateReason;
        this.studentClassTeacherEmail = studentClassTeacherEmail;
        this.studentClassTeacherResponse = studentClassTeacherResponse;
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String studentLeaveRequestId;

    private String studentLeaveRequestDate;
    private String studentLeaveRequestReason;
    private String studentClassTeacherEmail;
    private String studentClassTeacherResponse;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getLeaveRequestId() {
        return studentLeaveRequestId;
    }

    public void setLeaveRequestId(String leaveRequestId) {
        this.studentLeaveRequestId = leaveRequestId;
    }

    public String getStudentLeaveRequestDate() {
        return studentLeaveRequestDate;
    }

    public void setStudentLeaveRequestDate(String studentLeaveRequestDate) {
        this.studentLeaveRequestDate = studentLeaveRequestDate;
    }

    public String getStudentLeaveRequestReason() {
        return studentLeaveRequestReason;
    }

    public void setStudentLeaveRequestReason(String studentLeaveRequestDateReason) {
        this.studentLeaveRequestReason = studentLeaveRequestDateReason;
    }

    public String getStudentClassTeacherEmail() {
        return studentClassTeacherEmail;
    }

    public void setStudentClassTeacherEmail(String studentClassTeacherEmail) {
        this.studentClassTeacherEmail = studentClassTeacherEmail;
    }

    public String getStudentClassTeacherResponse() {
        return studentClassTeacherResponse;
    }

    public void setStudentClassTeacherResponse(String studentClassTeacherResponse) {
        this.studentClassTeacherResponse = studentClassTeacherResponse;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "student=" + student +
                ", leaveRequestId='" + studentLeaveRequestReason + '\'' +
                ", studentLeaveRequestDate='" + studentLeaveRequestDate + '\'' +
                ", studentLeaveRequestDateReason='" + studentLeaveRequestReason + '\'' +
                ", studentClassTeacherEmail='" + studentClassTeacherEmail + '\'' +
                ", studentClassTeacherResponse='" + studentClassTeacherResponse + '\'' +
                '}';
    }
}
