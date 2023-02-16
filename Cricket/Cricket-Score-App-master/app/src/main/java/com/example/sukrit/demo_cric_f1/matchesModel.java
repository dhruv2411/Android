package com.example.sukrit.demo_cric_f1;

import android.widget.ImageView;

/**
 * Created by Sukrit on 4/11/2017.
 */

public class matchesModel {

    String date;
    String team1;
    String team2;
    boolean matchStarted;
    Long unique_id;
    int img_num1;
    int img_num2;


    public matchesModel(String date, String team1, String team2, boolean matchStarted, Long unique_id)
    {
        this.date=date;
        this.team1=team1;
        this.team2=team2;
        this.matchStarted=matchStarted;
        this.unique_id=unique_id;

    }


    public String getDate() {
        return date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

}
