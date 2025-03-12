package com.school.school.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "achievements")
public class Achievements {

    public Achievements() {}

    public Achievements(Student student, Map<String, String> achievementsTrack) {
        this.student = student;
        this.achievementsTrack = achievementsTrack;
    }

    @Id
    @Column(name = "student_id")
    private String studentId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ElementCollection
    @CollectionTable(name = "achievements_track", joinColumns = @JoinColumn(name = "student_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "achievement_posted")
    private Map<String, String> achievementsTrack;

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
        if (student != null) {
            this.studentId = student.getStudentId();
        }
    }

    public Map<String, String> getAchievementsTrack() {
        return achievementsTrack;
    }

    public void setAchievementsTrack(Map<String, String> achievementsTrack) {
        this.achievementsTrack = achievementsTrack;
    }

    @Override
    public String toString() {
        return "Achievements{" +
                "studentId='" + studentId + '\'' +
                ", student=" + (student != null ? student.getStudentId() : "null") +
                ", achievementsTrack=" + achievementsTrack +
                '}';
    }
}