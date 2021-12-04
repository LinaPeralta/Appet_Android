package com.example.appet;

import androidx.annotation.NonNull;

public class Product {

    private int amount, use, days;
    private String name, measurement, id, date, dateBtnClick;
    private boolean button;

    public Product() {
    }

    public Product(int amount, int use, int days, String name, String measurement, String id, String date, String dateBtnClick, boolean button) {
        this.amount = amount;
        this.use = use;
        this.days = days;
        this.name = name;
        this.measurement = measurement;
        this.id = id;
        this.date = date;
        this.dateBtnClick = dateBtnClick;
        this.button = button;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateBtnClick() {
        return dateBtnClick;
    }

    public void setDateBtnClick(String dateBtnClick) {
        this.dateBtnClick = dateBtnClick;
    }

    public boolean isButton() {
        return button;
    }

    public void setButton(boolean button) {
        this.button = button;
    }
}