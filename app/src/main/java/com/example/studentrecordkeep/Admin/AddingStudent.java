package com.example.studentrecordkeep.Admin;

public class AddingStudent {

    private String name;
    private String rollno;
    private String depName;
    private String semester;
    private String year;
    private String Email;

    private String blockName;
    private String roomNumber;


    public AddingStudent() {

    }

    public AddingStudent(String name, String regNo, String depName, String semester, String year, String email) {
        this.name = name;
        this.rollno = regNo;
        this.depName = depName;
        this.semester = semester;
        this.year = year;
        Email = email;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return rollno;
    }

    public void setRegNo(String regNo) {
        this.rollno = regNo;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
