package com.example.TBSBank.enums;

public enum WithStatus {

    Pending("Pending"),
    Cancelled("Cancelled"),
    Completed("Completed");

    private final String status;

    private WithStatus(String s){
        status = s;
    }

    public String toString(){
        return this.status;
    }
}
