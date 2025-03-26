package com.school.school.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    public Student() {}

    public Student(String studentId, String studentName, String studentEmail, String studentClass, String studentSchool) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentClass = studentClass;
        this.studentSchool = studentSchool;
    }

    @Id
    private String studentId;
    private String studentName;
    private String studentEmail;
    private String studentClass;
    private String studentSchool;

    //Each student can have multiple attendance entries.
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Attendance> studentAttendance;

    //Each student can have multiple achievements entries.
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Achievements> studentAchievements;


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    //Each Student can have multiple leave entries.
    private List<LeaveRequest> studentLeaveRequests;


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    public List<Attendance> getStudentAttendance() {
        return studentAttendance;
    }

    public void setStudentAttendance(List<Attendance> studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    public List<Achievements> getStudentAchievements() {
        return studentAchievements;
    }

    public void setStudentAchievements(List<Achievements> studentAchievements) {
        this.studentAchievements = studentAchievements;
    }

    public List<LeaveRequest> getStudentLeaveRequests() {
        return studentLeaveRequests;
    }

    public void setStudentLeaveRequests(List<LeaveRequest> studentLeaveRequests) {
        this.studentLeaveRequests = studentLeaveRequests;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentSchool='" + studentSchool + '\'' +
                ", studentAttendance=" + studentAttendance +
                ", studentAchievements=" + studentAchievements +
                ", studentLeaveRequests=" + studentLeaveRequests +
                '}';
    }
}