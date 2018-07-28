package com.example.temo.myapplication;

public class Mitem {
    String name;
    String phone;
    String cost;
    String date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Mitem() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Mitem(String name, String phone, String cost, String method, String date) {

        this.name = name;
        this.phone = phone;
        this.cost = cost;
        this.method = method;
        this.date=date;

    }

    String method;

}
