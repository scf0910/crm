package com.yjxxt.model;

public class StudentModel {
    private String studentIdStr;
    private String studentName;
    private String trueName;

    public StudentModel() {
    }

    public String getStudentIdStr() {
        return studentIdStr;
    }

    public void setStudentIdStr(String studentIdStr) {
        this.studentIdStr = studentIdStr;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentIdStr='" + studentIdStr + '\'' +
                ", studentName='" + studentName + '\'' +
                ", trueName='" + trueName + '\'' +
                '}';
    }
}

