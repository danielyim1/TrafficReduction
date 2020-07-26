package com.example.trafficreduction.ui.notifications;



public class Profile{
    private String name;
    private int age;
    private String number;

    public Profile(String name, int age, String number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getNumber() {
        return number;
    }
}
