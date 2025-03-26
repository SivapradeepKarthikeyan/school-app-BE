package com.school.school.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {

    public Attendance() {}

    public Attendance(Student student, String date, boolean isPresent) {
        this.student = student;
        this.date = date;
        this.isPresent = isPresent;
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String attendanceId;
    private String date;
    private boolean isPresent;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(String attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "student=" + student +
                ", attendanceId='" + attendanceId + '\'' +
                ", date='" + date + '\'' +
                ", isPresent=" + isPresent +
                '}';
    }
}
