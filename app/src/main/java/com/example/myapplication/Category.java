package com.example.myapplication;

public class Category {
    public static final int Antivirus = 1;
    public static final int Firewall = 2;
    public static final int Zakladki = 3;
    public static final int KeySpies = 4;
    public static final int RSA = 5;


    private int id;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}