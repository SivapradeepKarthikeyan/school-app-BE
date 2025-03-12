package com.school.school.excelMigrators.dtos;

public class StudentDto {

    public StudentDto(){}

    public StudentDto(String studentId, String studentName, String studentEmail, String studentClass, String studentSchool) {
        this.studentId=studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentClass = studentClass;
        this.studentSchool = studentSchool;
    }

    private String studentId=null;
    private String studentName;
    private String studentEmail;
    private String studentClass;
    private String studentSchool;


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

    @Override
    public String toString() {
        return "StudentDto{" +
                "studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentClass='" + studentClass + '\'' +
                ", studentSchool='" + studentSchool + '\'' +
                '}';
    }
}
