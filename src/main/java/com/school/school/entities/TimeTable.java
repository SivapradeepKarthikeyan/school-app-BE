//package com.school.school.entities;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "time_table")
//public class TimeTable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String id;
//    private String class_;
//    private String school;
//
//    @Lob
//    @Column(name = "timetable_image", columnDefinition = "LONGBLOB")
//    private byte[] timetableImage;
//
//    public TimeTable() {}
//
//    public TimeTable(String class_, String school, byte[] timetableImage) {
//        this.class_ = class_;
//        this.school = school;
//        this.timetableImage = timetableImage;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getClass_() {
//        return class_;
//    }
//
//    public void setClass_(String class_) {
//        this.class_ = class_;
//    }
//
//    public String getSchool() {
//        return school;
//    }
//
//    public void setSchool(String school) {
//        this.school = school;
//    }
//
//    public byte[] getTimetableImage() {
//        return timetableImage;
//    }
//
//    public void setTimetableImage(byte[] timetableImage) {
//        this.timetableImage = timetableImage;
//    }
//}
