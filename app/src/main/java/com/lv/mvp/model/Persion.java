package com.lv.mvp.model;

/**
 * 作者：created by albert on 2019-07-26 16:38
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class Persion {
    private String userName;
    private int sex;
    private int age;
    private String brithDay;

    public Persion() {
    }

    public Persion(String userName, int sex, int age, String brithDay) {
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.brithDay = brithDay;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBrithDay() {
        return brithDay;
    }

    public void setBrithDay(String brithDay) {
        this.brithDay = brithDay;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "userName='" + userName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", brithDay='" + brithDay + '\'' +
                '}';
    }
}
