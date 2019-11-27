package com.example.TBSBank.enums;

public enum DepoType {

    P2P("P2P"),
    Deposit("Deposit"),
    Withdrawal("Withdrawal");


    private final String type;

    private DepoType(String s){
        type = s;
    }

    public String toString(){
        return this.type;
    }
}
