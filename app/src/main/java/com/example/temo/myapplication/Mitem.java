package com.example.temo.myapplication;

import java.io.Serializable;

class Mitem implements Serializable {
    private String name;
    private String phone;
    private String cost;
    private String date;
    private String method;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mitem(int id, String name, String phone, String date, String cost, String method) {

        this.name = name;
        this.phone = phone;
        this.cost = cost;
        this.method = method;
        this.date = date;
        this.id = id;


    }


}
