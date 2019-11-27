package com.example.TBSBank.enums;

public enum WithMedium {

    Balance("Balance"),
    Rewards("Rewards");

    private final String medium;

    private WithMedium(String s){
        medium = s;
    }

    public String toString(){

        return this.medium;
    }

}
