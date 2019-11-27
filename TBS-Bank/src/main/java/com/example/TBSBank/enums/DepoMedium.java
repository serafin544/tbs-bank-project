package com.example.TBSBank.enums;

public enum DepoMedium {

    Balance("Balance"),
    Rewards("Rewards");

    private final String medium;

    private DepoMedium(String s){
        medium = s;
    }

    public String toString(){

        return this.medium;
    }






}
