package com.school.school.entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "homework")
public class HomeWork {

    public HomeWork() {}

    public HomeWork(String id, String className, String school, String date, HashMap<String, String> homeWorks) {
        this.id = id;
        this.className = className;
        this.school = school;
        this.date = date;
        this.homeWorks = homeWorks;
    }

    @Id
    private String id;
    private String className;
    private String school;
    private String date; //DD-MM-YYYY

    @ElementCollection
    @CollectionTable(name = "homework_track", joinColumns = @JoinColumn(name = "id"))
    @MapKeyColumn(name = "subjects")
    @Column(name = "home_works")
    private Map<String,String> homeWorks;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String class_) {
        this.className = class_;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(HashMap<String, String> homeWorks) {
        this.homeWorks= homeWorks;
    }

    @Override
    public String toString() {
        return "HomeWork{" +
                "id='" + id + '\'' +
                ", className='" + className + '\'' +
                ", school='" + school + '\'' +
                ", date='" + date + '\'' +
                ", homeWorks=" + homeWorks +
                '}';
    }
}
