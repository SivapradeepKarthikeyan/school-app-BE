package com.school.school.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Attendance studentAttendance;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Achievements studentAchievements;

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

    public Attendance getStudentAttendance() {
        return studentAttendance;
    }

    public void setStudentAttendance(Attendance studentAttendance) {
        this.studentAttendance = studentAttendance;
    }

    public Achievements getStudentAchievements() {
        return studentAchievements;
    }

    public void setStudentAchievements(Achievements studentAchievements) {this.studentAchievements = studentAchievements;}

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
                '}';
    }
}