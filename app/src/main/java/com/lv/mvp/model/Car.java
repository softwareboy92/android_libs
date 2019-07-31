package com.lv.mvp.model;

/**
 * 作者：created by albert on 2019-07-31 18:38
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class Car {

    private String name;
    private String number;
    private String Color;

    public Car() {
    }

    public Car(String name, String number, String color) {
        this.name = name;
        this.number = number;
        Color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
