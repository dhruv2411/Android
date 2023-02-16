package com.example.sukrit.demo_cric_f1;

/**
 * Created by Sukrit on 4/11/2017.
 */

public class calendarModel {

    String date;
    String name;

    public calendarModel(String date,String name)
    {
        this.date=date;
        this.name=name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
