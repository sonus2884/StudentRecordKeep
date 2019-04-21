package com.example.studentrecordkeep.Admin;

public class AddingHall {

    private String hallName;
    private String roomNumber;
    private String blockNumber;
    private String studentNumber;
    private String staffName;

    public AddingHall() {
    }

    public AddingHall(String hallName, String roomNumber, String blockNumber, String studentNumber, String staffName) {
        this.hallName = hallName;
        this.roomNumber = roomNumber;
        this.blockNumber = blockNumber;
        this.studentNumber = studentNumber;
        this.staffName = staffName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
}
