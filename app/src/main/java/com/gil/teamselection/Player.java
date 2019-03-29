package com.gil.teamselection;


import java.io.Serializable;

public class Player implements Serializable {

    private String name;
    private double num;
    public  boolean isUsed;

    public Player(String name, double num ) {
        this.name = name;
        this.num = num;
          }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return name;
    }


}
