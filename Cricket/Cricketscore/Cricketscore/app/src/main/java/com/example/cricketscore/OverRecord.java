package com.example.cricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class OverRecord extends AppCompatActivity {
    private TextView bat1name, bat1runs, bat1balls, bat14s, bat16s, bat1sr;
    private TextView bat2name, bat2runs, bat2balls, bat24s, bat26s, bat2sr;
    private TextView bowlname, bowlovers, bowlruns, bowlmaiden, bowlwicks, bowleco;
    private ImageButton button_0, button_1, button_2,button_3,button_4,button_5,button_6,button_wide, button_noball, button_legbye;
    private ImageButton button_byes, button_wicket;

    private boolean iswide, isnoBall, islegBye, isbyes;

    private Button bowl_to_bat;
    private int striker;
    private boolean ball_num;
    private int over = 0, balls = 0, rBalls = 0;
    private Button scorebar;

    private int extras = 0;
    private int total_runs, wickets;
    private String battingTeam;
    private String bat1name_val, bat2name_val , bowlname_val;
    private int bat1runs_val, bat1balls_val , bat14s_val , bat16s_val ;
    private int bat2runs_val , bat2balls_val , bat24s_val , bat26s_val ;
    private int bowlovers_val , bowlruns_val , bowlmaiden_val , bowlwicks_val ;
    private float bowleco_val , bat2sr_val , bat1sr_val ;
    private boolean isExtra;

    private int match_id, bowler_id, batsman1_id, batsman2_id;
    private int inning;
    private int isOut;

    private ArrayList players1;
    private ArrayList players2;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_record);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHandler = new DatabaseHandler(getApplicationContext());
        match_id = 2002;

        players1 = new ArrayList();
        players2 = new ArrayList();

        for(int i = 101;i<=111;i++)
            players1.add(i);

        for(int i = 201;i<=211;i++)
            players2.add(i);

        batsman1_id = (int)players1.get(0);
        batsman2_id = (int)players1.get(1);
        bowler_id = (int)players2.get(0);

        total_runs = 0; wickets = 0;
        battingTeam = "India";
        bat1name_val = "Bat A"; bat2name_val = "Bat B"; bowlname_val = "Bowl B";
        bat1runs_val = 0; bat1balls_val = 0; bat14s_val = 0; bat16s_val = 0;
        bat2runs_val = 0; bat2balls_val = 0; bat24s_val = 0; bat26s_val = 0;
        bowlovers_val = 0; bowlruns_val = 0; bowlmaiden_val = 0; bowlwicks_val = 0;
        bowleco_val = 0; bat2sr_val = 0; bat1sr_val = 0;

        isOut = 0;

        bat1name = (TextView)findViewById(R.id.bname);
        bat1runs = (TextView)findViewById(R.id.bruns);
        bat1balls = (TextView)findViewById(R.id.bballs);
        bat14s =(TextView)findViewById(R.id.b4s);
        bat16s = (TextView)findViewById(R.id.b6s);
        bat1sr = (TextView)findViewById(R.id.bsr);

        bowlname = (TextView)findViewById(R.id.bowlname);
        bowlovers = (TextView)findViewById(R.id.bowlovers);
        bowlruns =(TextView)findViewById(R.id.bowlruns);
        bowlmaiden = (TextView)findViewById(R.id.bowlmaiden);
        bowlwicks = (TextView)findViewById(R.id.bowlwicks);
        bowleco = (TextView)findViewById(R.id.bowleco);

        bat2name = (TextView)findViewById(R.id.b1name);
        bat2runs = (TextView)findViewById(R.id.b1runs);
        bat2balls = (TextView)findViewById(R.id.b1balls);
        bat24s =(TextView)findViewById(R.id.b14s);
        bat26s = (TextView)findViewById(R.id.b16s);
        bat2sr = (TextView)findViewById(R.id.b1sr);

        bowl_to_bat = (Button)findViewById(R.id.bowl_to_bat_label);
        bowl_to_bat.setText(bowlname_val + " to " + bat1name_val);
        ball_num = false;

        scorebar = (Button)findViewById(R.id.scorebar);

        //updateLabels();

        bat1name.setText("" + bat1name_val);
        bat1runs.setText("" + bat1runs_val);
        bat1balls.setText("" + bat1balls_val);
        bat14s.setText("" + bat14s_val);
        bat16s.setText("" + bat16s_val);
        bat1sr.setText(Float.toString(bat1sr_val));

        bowlname.setText(bowlname_val);
        bowlmaiden.setText("" +bowlmaiden_val);
        bowlovers.setText(bowlovers_val + "."+ balls);
        bowlruns.setText("" + bowlruns_val);
        bowlwicks.setText("" + bowlwicks_val);
        bowleco.setText(Float.toString(bowleco_val));

        bat2name.setText(bat2name_val);
        bat2runs.setText("" + bat2runs_val);
        bat2balls.setText("" + bat2balls_val);
        bat24s.setText("" + bat24s_val);
        bat26s.setText("" + bat26s_val);
        bat2sr.setText(Float.toString(bat2sr_val));

        if(striker == 1)
            bowl_to_bat.setText(bowlname_val + " to " + bat1name_val);
        else
            bowl_to_bat.setText(bowlname_val + " to " + bat2name_val);

        scorebar.setText(battingTeam + ": " + total_runs + "/" + wickets);


        striker = 1;

        over = 0;
        balls = 0;
        rBalls = 0;

        bowl_to_bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ball_num) {
                    if (striker == 1)
                        bowl_to_bat.setText(bowlname_val + " to " + bat2name_val);
                    else
                        bowl_to_bat.setText(bowlname_val + " to " + bat1name_val);
                } else
                    bowl_to_bat.setText("Over " + over + "." + balls);
                ball_num = !ball_num;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        button_0 = (ImageButton)findViewById(R.id.button_0);
        button_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iswide){
                    iswide = !iswide;
                    updateLabels(v); recordBall(balls, striker, 0, 1);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    updateLabels(v); recordBall(balls, striker, 0, 0);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    updateLabels(v); recordBall(balls, striker, 0, 1);
                    return;
                }
                else if(islegBye){
                    islegBye= !islegBye;
                    updateLabels(v); recordBall(balls, striker, 0, 0);
                    return;
                }
                balls++; rBalls++;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }
                updateLabels(v); recordBall(balls, striker, 0, 0);

            }
        });

        button_1 = (ImageButton)findViewById(R.id.button_1);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iswide){
                    iswide = !iswide;
                    total_runs +=1;
                    extras += 1;
                    updateLabels(v); recordBall(balls, striker, 0, 1);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    total_runs +=1;
                    extras += 1;
                    updateLabels(v); recordBall(balls, striker, 0, 1);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    total_runs +=1;
                    extras += 1;
                    if(striker==1) {
                        //bat1balls_val += 1;
                        bat1runs_val += 1;
                        bat1sr_val = bat1runs_val*100/bat1balls_val;
                    }
                    else {
                        // bat2balls_val += 1;
                        bat2runs_val += 1;
                        bat2sr_val = bat2runs_val*100/bat2balls_val;
                    }

                    striker = (2*striker)%3;

                    bowlruns_val += 1;
                    bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                    updateLabels(v); recordBall(balls, striker, 1, 1);
                    return;
                }
                else if(islegBye){
                    islegBye = !islegBye;
                    extras += 1;
                    striker = (2*striker)%3;
                    total_runs +=1;
                    updateLabels(v); recordBall(balls, striker, 0, 1);
                    return;
                }
                balls++; rBalls++;

                bowlruns_val += 1;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1runs_val += 1;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2runs_val += 1;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }

                total_runs++;
                striker = (2*striker)%3;

                updateLabels(v); recordBall(balls, striker, 1, 0);

            }
        });

        button_2 = (ImageButton)findViewById(R.id.button_2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iswide){
                    iswide = !iswide;
                    total_runs +=2;
                    extras += 2;
                    updateLabels(v); recordBall(balls, striker, 0, 2);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    extras += 2;
                    total_runs +=2;
                    updateLabels(v); recordBall(balls, striker, 0, 2);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    extras += 2;
                    total_runs +=2;
                    if(striker==1) {
                        //bat1balls_val += 1;
                        bat1runs_val += 2;
                        bat1sr_val = bat1runs_val*100/bat1balls_val;
                    }
                    else {
                        // bat2balls_val += 1;
                        bat2runs_val += 2;
                        bat2sr_val = bat2runs_val*100/bat2balls_val;
                    }
                    bowlruns_val += 2;
                    bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                    updateLabels(v); recordBall(balls, striker, 2, 1);
                    return;
                }
                else if(islegBye){
                    islegBye = !islegBye;
                    total_runs +=2;
                    extras += 2;
                    updateLabels(v); recordBall(balls, striker, 0, 2);
                    return;
                }
                balls++; rBalls++;

                bowlruns_val += 2;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1runs_val += 2;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2runs_val += 2;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }

                total_runs += 2;
                updateLabels(v); recordBall(balls, striker, 2, 0);


            }
        });

        button_3 = (ImageButton)findViewById(R.id.button_3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iswide){
                    iswide = !iswide;
                    total_runs +=3;
                    extras += 3;
                    updateLabels(v); recordBall(balls, striker, 0, 3);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    extras += 3;
                    total_runs +=3;
                    updateLabels(v); recordBall(balls, striker, 0, 3);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    extras += 1;
                    total_runs +=3;
                    if(striker==1) {
                        //bat1balls_val += 1;
                        bat1runs_val += 3;
                        bat1sr_val = bat1runs_val*100/bat1balls_val;
                    }
                    else {
                        // bat2balls_val += 1;
                        bat2runs_val += 3;
                        bat2sr_val = bat2runs_val*100/bat2balls_val;
                    }

                    striker = (2*striker)%3;

                    bowlruns_val += 3;
                    bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                    updateLabels(v);recordBall(balls, striker, 3, 1);
                    return;
                }
                else if(islegBye){
                    islegBye = !islegBye;
                    total_runs +=3;
                    extras += 3;
                    updateLabels(v); recordBall(balls, striker, 0, 3);
                    return;
                }
                balls++; rBalls++;

                bowlruns_val += 3;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1runs_val += 3;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2runs_val += 3;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }


                total_runs += 3;

                updateLabels(v); recordBall(balls, striker, 3, 0);


            }
        });
        button_4 = (ImageButton)findViewById(R.id.button_4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iswide){
                    iswide = !iswide;
                    total_runs +=4;
                    extras += 4;
                    updateLabels(v); recordBall(balls, striker, 0, 4);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    extras += 4;
                    total_runs +=4;
                    updateLabels(v); recordBall(balls, striker, 0, 4);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    extras += 1;
                    total_runs +=4;
                    if(striker==1) {
                        //bat1balls_val += 1;
                        bat1runs_val += 4;
                        bat14s_val++;
                        bat1sr_val = bat1runs_val*100/bat1balls_val;
                    }
                    else {
                        // bat2balls_val += 1;
                        bat2runs_val += 4;
                        bat24s_val++;
                        bat2sr_val = bat2runs_val*100/bat2balls_val;
                    }

                    striker = (2*striker)%3;

                    bowlruns_val += 4;
                    bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                    updateLabels(v); recordBall(balls, striker, 4, 1);
                    return;
                }
                else if(islegBye){
                    islegBye = !islegBye;
                    total_runs +=4;
                    extras += 4;
                    updateLabels(v); recordBall(balls, striker, 0, 4);
                    return;
                }
                balls++; rBalls++;

                bowlruns_val += 4;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1runs_val += 4;
                    bat14s_val++;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2runs_val += 4;
                    bat24s_val++;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }

                total_runs += 4;

                updateLabels(v); recordBall(balls, striker, 4, 0);
            }
        });

        button_5 = (ImageButton)findViewById(R.id.button_5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iswide){
                    iswide = !iswide;
                    total_runs +=5;
                    extras += 5;
                    updateLabels(v); recordBall(balls, striker, 0, 5);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    extras += 5;
                    total_runs +=5;
                    updateLabels(v); recordBall(balls, striker, 0, 5);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    extras += 1;
                    total_runs +=5;
                    if(striker==1) {
                        //bat1balls_val += 1;
                        bat1runs_val += 5;
                        bat1sr_val = bat1runs_val*100/bat1balls_val;
                    }
                    else {
                        // bat2balls_val += 1;
                        bat2runs_val += 5;
                        bat2sr_val = bat2runs_val*100/bat2balls_val;
                    }

                    striker = (2*striker)%3;

                    bowlruns_val += 5;
                    bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                    updateLabels(v); recordBall(balls, striker, 5, 1);
                    return;
                }
                else if(islegBye){
                    islegBye = !islegBye;
                    total_runs +=5;
                    extras += 5;
                    updateLabels(v); recordBall(balls, striker, 0, 5);
                    return;
                }
                balls++; rBalls++;

                bowlruns_val += 5;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1runs_val += 5;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2runs_val += 5;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }


                total_runs += 5;

                striker = (2*striker)%3;

                updateLabels(v); recordBall(balls, striker, 5, 0);
            }
        });

        button_6 = (ImageButton)findViewById(R.id.button_6);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(iswide){
                    iswide = !iswide;
                    total_runs +=6;
                    extras += 6;
                    updateLabels(v); recordBall(balls, striker, 0, 6);
                    return;
                }
                else if(isbyes){
                    isbyes = !isbyes;
                    extras += 6;
                    total_runs +=6;
                    updateLabels(v); recordBall(balls, striker, 0, 6);
                    return;
                }
                else if(isnoBall){
                    isnoBall = !isnoBall;
                    extras += 1;
                    total_runs +=6;
                    if(striker==1) {
                        //bat1balls_val += 1;
                        bat1runs_val += 6;
                        bat16s_val++;
                        bat1sr_val = bat1runs_val*100/bat1balls_val;
                    }
                    else {
                        // bat2balls_val = 1;
                        bat2runs_val += 6;
                        bat26s_val++;
                        bat2sr_val = bat2runs_val*100/bat2balls_val;
                    }

                    striker = (2*striker)%3;

                    bowlruns_val += 6;
                    bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                    updateLabels(v); recordBall(balls, striker, 6, 1);
                    return;
                }
                else if(islegBye){
                    islegBye = !islegBye;
                    total_runs +=6;
                    extras += 6;
                    updateLabels(v); recordBall(balls, striker, 0, 6);
                    return;
                }

                balls++; rBalls++;

                bowlruns_val += 6;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));

                if(striker==1) {
                    bat1balls_val += 1;
                    bat1runs_val += 6;
                    bat16s_val++;
                    bat1sr_val = bat1runs_val*100/bat1balls_val;
                }
                else {
                    bat2balls_val += 1;
                    bat2runs_val += 6;
                    bat26s_val++;
                    bat2sr_val = bat2runs_val*100/bat2balls_val;
                }

                total_runs += 6;

                updateLabels(v); recordBall(balls, striker, 6, 0);

                //Over_info test = dbHandler.getOver_info(match_id, inning,over,balls);
                //Snackbar.make(v, test.get_match_id() + ", " + test.get_inning() + ", " + test.get_over_id() + ", " + test.get_ball_num()
                //      + ", " + test.get_batsman_id() + ", " + test.get_extra() + ", " + test.get_run_By_Bat() + ", " + test.get_out(), Snackbar.LENGTH_LONG);
            }
        });

        button_wide = (ImageButton)findViewById(R.id.button_wide);
        button_wide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_runs += 1;
                iswide = true;
                rBalls++;
                isExtra = true;
                bowlruns_val++;
                extras++;
                Snackbar.make(v, "How many runs scored on this ball.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button_noball = (ImageButton)findViewById(R.id.button_noball);
        button_noball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_runs += 1;
                isnoBall = true;
                bowlruns_val++;
                rBalls++;
                isExtra = true;
                extras++;
                Snackbar.make(v, "How many runs scored on this ball.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button_legbye = (ImageButton)findViewById(R.id.button_legbye);
        button_legbye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_runs += 1;
                islegBye = true;
                isExtra = true;
                //bowlruns_val++;
                //extras++;
                balls++; rBalls++;
                Snackbar.make(v, "How many runs scored on this ball.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button_byes = (ImageButton)findViewById(R.id.button_byes);
        button_byes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_runs += 1;
                isbyes = true;
                isExtra = true;
                //bowlruns_val++;
                //extras++;
                balls++; rBalls++;

                Snackbar.make(v, "How many runs scored on this ball.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button_wicket = (ImageButton)findViewById(R.id.button_wicket);
        button_wicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //total_runs += 1;
                wickets++;

                //bowlruns_val++;
                //extras++;
                balls++; rBalls++;
                bowlwicks_val++;
                bowleco_val = bowlruns_val/(bowlovers_val + (float)(0.166*balls));
                isOut = 1;
                updateLabels(v);
                recordBall(balls, striker, 0, 0);
                isOut = 0;
            }
        });
    }

    private void updateLabels(View v){
        bat1name.setText("" + bat1name_val);
        bat1runs.setText("" + bat1runs_val);
        bat1balls.setText("" + bat1balls_val);
        bat14s.setText("" + bat14s_val);
        bat16s.setText("" + bat16s_val);
        bat1sr.setText(Float.toString(bat1sr_val));

        bowlname.setText(bowlname_val);
        bowlmaiden.setText("" +bowlmaiden_val);
        bowlovers.setText(bowlovers_val + "."+ balls);
        bowlruns.setText("" + bowlruns_val);
        bowlwicks.setText("" + bowlwicks_val);
        bowleco.setText(Float.toString(bowleco_val));

        bat2name.setText(bat2name_val);
        bat2runs.setText("" + bat2runs_val);
        bat2balls.setText("" + bat2balls_val);
        bat24s.setText("" + bat24s_val);
        bat26s.setText("" + bat26s_val);
        bat2sr.setText(Float.toString(bat2sr_val));

        if(striker == 1) {
            bowl_to_bat.setText(bowlname_val + " to " + bat1name_val);

        }else
            bowl_to_bat.setText(bowlname_val + " to " + bat2name_val);

        scorebar.setText(battingTeam + ": " + total_runs + "/" + wickets);

        if(balls== 6){
            Snackbar.make(v, "Over just finished.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        if(wickets == 10){
            Snackbar.make(v, "All Out.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

            endmatch();

        }

        if(over == 10){
            endmatch();
        }

    }

    private void recordBall(int ball_num, int striker, int runsByBat,int extraRuns){
        int inning = 1;
        Over_info new_ball;

        System.out.println(over+" "+rBalls+" "+runsByBat+" "+extraRuns);
        if(striker == 1)
            new_ball = new Over_info(match_id, inning, over, rBalls, batsman1_id, runsByBat, isOut, extraRuns);
        else
            new_ball = new Over_info(match_id, inning, over, rBalls, batsman2_id, runsByBat, isOut, extraRuns);

        dbHandler.addOver_info(new_ball);

        if(balls == 6) {
            //dbHandler.updateplayedmatch(match_id, 11);
            inning = 1;
            dbHandler.sumOver(match_id, inning, over, bowler_id);
            over++;
            balls = 0;
            rBalls = 0;
            bowlovers_val++;

        }
    }
    public void endmatch(){

        for(int i = 0;i<players1.size();i++){
            int k = (int)players1.get(i);
            dbHandler.updateplayedmatch(match_id, k);
        }
        for(int i = 0;i<players2.size();i++){
            int k = (int)players2.get(i);
            dbHandler.updateplayedmatch(match_id, k);
        }

        Intent ret = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(ret);
    }

}
