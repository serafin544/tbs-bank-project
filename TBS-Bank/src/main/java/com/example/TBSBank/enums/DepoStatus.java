package com.example.TBSBank.enums;

import com.example.TBSBank.models.Deposit;

public enum DepoStatus {

    Pending("Pending"),
    Cancelled("Cancelled"),
    Completed("Completed");

    private final String status;

    private DepoStatus(String s){
        status = s;
    }

    public String toString(){
        return this.status;
    }
}
