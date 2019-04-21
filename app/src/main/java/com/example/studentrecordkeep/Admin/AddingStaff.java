package com.example.studentrecordkeep.Admin;

public class AddingStaff {

    private String name;
    private String email;
    private String depName;
    private String staffId;

    public AddingStaff() {
    }

    public AddingStaff(String name, String email, String depName,String staffId) {
        this.name = name;
        this.email = email;
        this.depName = depName;
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
