package com.school.school.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "attendance")
public class Attendance {

    public Attendance() {}

    public Attendance(Student student, Map<String, Boolean> attendanceTrack) {
        this.student = student;
        this.attendanceTrack = attendanceTrack;
    }

    @Id //Creating the id
    @Column(name = "student_id")
    private String studentId;

    @MapsId //Mapping the id with student
    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ElementCollection
    @CollectionTable(name = "attendance_track", joinColumns = @JoinColumn(name = "student_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "is_present")
    private Map<String, Boolean> attendanceTrack;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
        this.studentId = student.getStudentId();
    }

    public Map<String, Boolean> getAttendanceTrack() {
        return attendanceTrack;
    }

    public void setAttendanceTrack(Map<String, Boolean> attendanceTrack) {
        this.attendanceTrack = attendanceTrack;
    }
}
