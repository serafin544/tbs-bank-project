package com.example.TBSBank.enums;

public enum WithType {

    P2P("P2P"),
    Deposit("Deposit"),
    Withdrawal("Withdrawal");


    private final String type;

    private WithType(String s){
        type = s;
    }

    public String toString(){
        return this.type;
    }
}
