package com.gil.teamselection;

public class Player {

    private String name;
    private String num;
    public  boolean isUsed;

    public Player(String name, String num, boolean isUsed) {
        this.name = name;
        this.num = num;
        this.isUsed=isUsed;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return name;
    }
}
