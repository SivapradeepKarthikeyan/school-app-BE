package com.school.school.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "achievements")
public class Achievements {

    public Achievements() {}

    public Achievements(Student student, String achievementDate, String achievementTitle, String achievementLink) {
        this.student = student;
        this.achievementDate = achievementDate;
        this.achievementTitle = achievementTitle;
        this.achievementLink = achievementLink;
    }

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String achievementId;
    private String achievementDate;
    private String achievementTitle;
    private String achievementLink;


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public String getAchievementDate() {
        return achievementDate;
    }

    public void setAchievementDate(String achievementDate) {
        this.achievementDate = achievementDate;
    }

    public String getAchievementTitle() {
        return achievementTitle;
    }

    public void setAchievementTitle(String achievementTitle) {
        this.achievementTitle = achievementTitle;
    }

    public String getAchievementLink() {
        return achievementLink;
    }

    public void setAchievementLink(String achievementLink) {
        this.achievementLink = achievementLink;
    }

    @Override
    public String toString() {
        return "Achievements{" +
                "student=" + student +
                ", achievementId='" + achievementId + '\'' +
                ", achievementDate='" + achievementDate + '\'' +
                ", achievementTitle='" + achievementTitle + '\'' +
                ", achievementLink='" + achievementLink + '\'' +
                '}';
    }
}