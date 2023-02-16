package com.example.cricket;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RunActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView toss, battingTeam, nonBatting, nonBatting_run, nonBatting_wicket, rundot, run1, run2, run3, run4, run6, runWd, runXtra, runNB, wktBwld, wktC, wktLBW, wktRO, wktSTM, wktHW;
    String tossName, bat_1;
    int Innings = 1, RUN = 0, WICKET = 0;
    double runrate = 0.0, reqdrate = 0.0, projScore;
    int bat1R = 0, bat1B = 0, bat2R = 0, bat2B = 0, bowl1R = 0, bowl1W = 0, bowl2R = 0, bowl2W = 0, balls = 0, overs = 0;
    TextView bat_RUN, bat_WKT, no_Overs, no_Balls, run_2_win, bat1, bat2,ball1,ball2,ball1_strike,ball2_strike,ball1wkt,ball2wkt;
    TextView bat1_run, bat2_run, bat1_ball, bat2_ball, bowl1_run, bowl1_wkt, bowl2_run, bowl2_wkt, bat1_strike, bat2_strike, bowl1_strike, bowl2_strike;
    String winner;
    int team1RUN, team2RUN, team1WKT, team2WKT, no = 0, no1 = 0, tot_wicket;

    void innings_change()
    {
        if (Innings == 2) {
            bat1.setText(Values.teamPlayer2[no1++]);
            bat2.setText(Values.teamPlayer2[no1++]);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toss = (TextView) findViewById(R.id.toss);
        battingTeam = (TextView) findViewById(R.id.batting_team_name);
        nonBatting = (TextView) findViewById(R.id.non_batting_team);
        nonBatting_run = (TextView) findViewById(R.id.non_batting_team_run);
        nonBatting_wicket = (TextView) findViewById(R.id.non_batting_team_wicket);

        Intent intent = getIntent();
        tossName = intent.getStringExtra("key");
        bat_1 = intent.getStringExtra("key2");

        toss.setText(tossName);
        battingTeam.setText(bat_1);
        nonBatting.setText(Values.team2);
        if (Innings == 1) {
            nonBatting_run.setText(" ");
            nonBatting_wicket.setText(" ");
        }

        bat_RUN = (TextView) findViewById(R.id.batting_run);
        bat_WKT = (TextView) findViewById(R.id.batting_wkt);
        rundot = (TextView) findViewById(R.id.run_dot);
        run1 = (TextView) findViewById(R.id.run_1);
        run2 = (TextView) findViewById(R.id.run_2);
        run3 = (TextView) findViewById(R.id.run_3);
        run4 = (TextView) findViewById(R.id.run_4);
        run6 = (TextView) findViewById(R.id.run_6);
        runWd = (TextView) findViewById(R.id.run_wide);
        runXtra = (TextView) findViewById(R.id.run_extra);
        runNB = (TextView) findViewById(R.id.run_noball);
        wktBwld = (TextView) findViewById(R.id.wkt_bowled);
        wktC = (TextView) findViewById(R.id.wkt_caught);
        wktLBW = (TextView) findViewById(R.id.wkt_lbw);
        wktRO = (TextView) findViewById(R.id.wkt_rout);
        wktSTM = (TextView) findViewById(R.id.wkt_stumped);
        wktHW = (TextView) findViewById(R.id.wkt_hwkt);
        bat1_run = (TextView) findViewById(R.id.batsman1_run);
        bat1_ball = (TextView) findViewById(R.id.batsman1_ball);
        bat1_strike = (TextView) findViewById(R.id.batsman1_strike);
        bat2_run = (TextView) findViewById(R.id.batsman2_run);
        bat2_ball = (TextView) findViewById(R.id.batsman2_ball);
        bat2_strike = (TextView) findViewById(R.id.batsman2_strike);
        //bowl1_run = (TextView) findViewById(R.id.bowler1_run);
        bowl1_wkt = (TextView) findViewById(R.id.bowler1_wicket);
        bowl1_strike = (TextView) findViewById(R.id.bowler1_strike);
        //bowl2_run = (TextView) findViewById(R.id.bowler2_run);
        bowl2_wkt = (TextView) findViewById(R.id.bowler2_wicket);
        bowl2_strike = (TextView) findViewById(R.id.bowler2_strike);
        no_Balls = (TextView) findViewById(R.id.no_of_balls);
        no_Overs = (TextView) findViewById(R.id.no_of_overs);
        run_2_win = (TextView) findViewById(R.id.run2win);
        bat1 = (TextView) findViewById(R.id.batsman1);
        bat2 = (TextView) findViewById(R.id.batsman2);
        ball1 = (TextView) findViewById(R.id.bowler1);
        ball2 = (TextView) findViewById(R.id.bowler2);



        if (Innings == 1) {
            bat1.setText(Values.teamPlayer1[no++]);
            bat2.setText(Values.teamPlayer1[no++]);
            ball1.setText(Values.teamPlayer2[Values.playersNum-1]);
            ball2.setText(Values.teamPlayer2[Values.playersNum-2]);
        }
        if (Innings == 2) {
            bat1.setText(Values.teamPlayer2[no1++]);
            bat2.setText(Values.teamPlayer2[no1++]);
            ball1.setText(Values.teamPlayer1[Values.playersNum-1]);
            ball2.setText(Values.teamPlayer1[Values.playersNum-2]);
        }

        tot_wicket = (Values.playersNum) - 1;

        if (Integer.parseInt(no_Overs.getText().toString()) <= Values.overs && WICKET <= tot_wicket) {
            rundot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 0;
                    bat_RUN.setText(RUN + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 0) + "");
                        bat1_ball.setText((bat1B += 1) + "");
                    } else {
                        bat2_run.setText((bat2R += 0) + "");
                        bat2_ball.setText((bat2B += 1) + "");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    innings_change();
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            run1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 1;
                    bat_RUN.setText(RUN + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 1) + "");
                        bat1_ball.setText((bat1B += 1) + "");
                        bat1_strike.setText("");
                        bat2_strike.setText("*");
                    } else if (bat2_strike.getText().equals("*")) {
                        bat2_run.setText((bat2R += 1) + "");
                        bat2_ball.setText((bat2B += 1) + "");
                        bat1_strike.setText("*");
                        bat2_strike.setText("");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            //runReqd();
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                                Intent i0 = new Intent(RunActivity.this, launching.class);
                                startActivity(i0);
                            }
                        }
                    }
                }
            });
            run2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 2;
                    bat_RUN.setText(RUN + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 2) + "");
                        bat1_ball.setText((bat1B += 1) + "");
                    } else {
                        bat2_run.setText((bat2R += 2) + "");
                        bat2_ball.setText((bat2B += 1) + "");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            run3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 3;
                    bat_RUN.setText(RUN + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 3) + "");
                        bat1_ball.setText((bat1B += 1) + "");
                        bat1_strike.setText("");
                        bat2_strike.setText("*");
                    } else if (bat2_strike.getText().equals("*")) {
                        bat2_run.setText((bat2R += 3) + "");
                        bat2_ball.setText((bat2B += 1) + "");
                        bat2_strike.setText("");
                        bat1_strike.setText("*");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            run4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 4;
                    bat_RUN.setText(RUN + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 4) + "");
                        bat1_ball.setText((bat1B += 1) + "");
                    } else {
                        bat2_run.setText((bat2R += 4) + "");
                        bat2_ball.setText((bat2B += 1) + "");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            run6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 6;
                    bat_RUN.setText(RUN + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 6) + "");
                        bat1_ball.setText((bat1B += 1) + "");
                    } else {
                        bat2_run.setText((bat2R += 6) + "");
                        bat2_ball.setText((bat2B += 1) + "");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            runWd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 1;
                    bat_RUN.setText(RUN + "");
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 0) + "");
                        bat1_ball.setText((bat1B += 0) + "");
                    } else {
                        bat2_run.setText((bat2R += 0) + "");
                        bat2_ball.setText((bat2B += 0) + "");
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            runXtra.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 1;
                    bat_RUN.setText(RUN + "");
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 0) + "");
                        bat1_ball.setText((bat1B += 0) + "");
                    } else {
                        bat2_run.setText((bat2R += 0) + "");
                        bat2_ball.setText((bat2B += 0) + "");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            runNB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RUN += 1;
                    bat_RUN.setText(RUN + "");
                    if (bat1_strike.getText().equals("*")) {
                        bat1_run.setText((bat1R += 0) + "");
                        bat1_ball.setText((bat1B += 0) + "");
                    } else {
                        bat2_run.setText((bat2R += 0) + "");
                        bat2_ball.setText((bat2B += 0) + "");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            wktBwld.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WICKET += 1;
                    innings_change();
                    bat_WKT.setText("/" + WICKET + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                        bowl1_strike.setText("");
                        bowl2_strike.setText("*");
                    }
                    if(bowl1_strike.getText().equals("*"))
                    {
                        bowl1_wkt.setText(bowl1W++ +"");
                    }
                    else
                        bowl2_wkt.setText(bowl2W++ +"");
                    if (bat1_strike.getText().equals("*")) {
                        if (Innings == 1) {
                            bat1.setText(Values.teamPlayer1[no++]);

                        }
                        if (Innings == 2) {
                            bat1.setText(Values.teamPlayer2[no1++]);
                        }
                        bat1_run.setText((0) + "");
                        bat1_ball.setText((0) + "");
                        bat1R = 0;
                        bat1B = 0;
                    } else {
                        if (Innings == 1) {
                            bat2.setText(Values.teamPlayer1[no++]);
                        }
                        if (Innings == 2) {
                            bat2.setText(Values.teamPlayer2[no++]);
                        }
                        bat2_run.setText((0) + "");
                        bat2_ball.setText((0) + "");
                        bat2R = 0;
                        bat2B = 0;
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            wktC.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WICKET += 1;
                    bat_WKT.setText("/" + WICKET + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1.setText(Values.teamPlayer1[no++]);
                        bat1_run.setText((0) + "");
                        bat1_ball.setText((0) + "");
                        bat1R = 0;
                        bat1B = 0;
                    } else {
                        bat2.setText(Values.teamPlayer1[no++]);
                        bat2_run.setText((0) + "");
                        bat2_ball.setText((0) + "");
                        bat2R = 0;
                        bat2B = 0;
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            wktLBW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WICKET += 1;
                    bat_WKT.setText("/" + WICKET + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1.setText(Values.teamPlayer1[no++]);
                        bat1_run.setText((0) + "");
                        bat1_ball.setText((0) + "");
                        bat1R = 0;
                        bat1B = 0;
                    } else {
                        bat2.setText(Values.teamPlayer1[no++]);
                        bat2_run.setText((0) + "");
                        bat2_ball.setText((0) + "");
                        bat2R = 0;
                        bat2B = 0;
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            innings_change();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Values.inn=1;
                                Innings = 1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            wktRO.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WICKET += 1;
                    bat_WKT.setText("/" + WICKET + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1.setText(Values.teamPlayer1[no++]);
                        bat1_run.setText((0) + "");
                        bat1_ball.setText((0) + "");
                        bat1R = 0;
                        bat1B = 0;
                    } else {
                        bat2.setText(Values.teamPlayer1[no++]);
                        bat2_run.setText((0) + "");
                        bat2_ball.setText((0) + "");
                        bat2R = 0;
                        bat2B = 0;
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            wktSTM.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WICKET += 1;
                    bat_WKT.setText("/" + WICKET + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1.setText(Values.teamPlayer1[no++]);
                        bat1_run.setText((0) + "");
                        bat1_ball.setText((0) + "");
                        bat1R = 0;
                        bat1B = 0;
                    } else {
                        bat2.setText(Values.teamPlayer1[no++]);
                        bat2_run.setText((0) + "");
                        bat2_ball.setText((0) + "");
                        bat2R = 0;
                        bat2B = 0;
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
            wktHW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WICKET += 1;
                    bat_WKT.setText("/" + WICKET + "");
                    if (balls < 6) {
                        no_Balls.setText((balls += 1) + "");
                    }
                    if (balls > 5) {
                        balls = 0;
                        no_Overs.setText((overs += 1) + "");
                        no_Balls.setText(balls + "");
                    }
                    if (bat1_strike.getText().equals("*")) {
                        bat1.setText(Values.teamPlayer1[no++]);
                        bat1_run.setText((0) + "");
                        bat1_ball.setText((0) + "");
                        bat1R = 0;
                        bat1B = 0;
                    } else {
                        bat2.setText(Values.teamPlayer1[no++]);
                        bat2_run.setText((0) + "");
                        bat2_ball.setText((0) + "");
                        bat2R = 0;
                        bat2B = 0;
                    }
                    runrate = Integer.parseInt(bat_RUN.getText().toString()) / ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString())) * 6;
                    if (Innings == 1) {
                        projScore = Values.overs * runrate;
                        run_2_win.setText("Projected Score : " + (int) projScore);
                    }
                    if (Innings == 2) {
                        int reqdRun = team1RUN - RUN;
                        int reqdBall = (Values.overs * 6) - ((Integer.parseInt(no_Overs.getText().toString()) * 6) + Integer.parseInt(no_Balls.getText().toString()));
                        run_2_win.setText("Need " + (reqdRun + 1) + " runs from " + reqdBall + " balls");
                    }
                    if (Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                        if (Innings == 1) {
                            new AlertDialog.Builder(RunActivity.this)
                                    .setTitle("End of Innings")
                                    .setMessage(Innings + " innings is over\nScore : " + RUN + " /" + WICKET + "\n")
                                    .setCancelable(true)
                                    .show();
                            team1RUN = RUN;
                            RUN = 0;
                            team1WKT = WICKET;
                            WICKET = 0;
                            overs = 0;
                            balls = 0;
                            Innings = 2;
                            Values.inn=2;
                            battingTeam.setText(nonBatting.getText().toString());
                            nonBatting.setText(bat_1);
                            nonBatting_run.setText(team1RUN + "");
                            nonBatting_wicket.setText(team1WKT + "");
                            refresh();
                        }
                        if (Innings == 2) {
                            if (team2RUN > team1RUN || Integer.parseInt(no_Overs.getText().toString()) == Values.overs || WICKET == tot_wicket) {
                                team2RUN = RUN;
                                RUN = 0;
                                team2WKT = WICKET;
                                WICKET = 0;
                                overs = 0;
                                balls = 0;
                                Innings = 1;
                                Values.inn=1;
                                if (team2RUN > team1RUN)
                                    winner = Values.team2;
                                else if (team2RUN == team1RUN) {
                                    winner = "tied";
                                } else
                                    winner = Values.team1;
                                if (winner != "tied") {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Over!!\n" + "Winner is :" + winner)
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                } else {
                                    new AlertDialog.Builder(RunActivity.this)
                                            .setTitle("End of Match")
                                            .setMessage("Match Tied")
                                            .setCancelable(true)
                                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                                @Override
                                                public void onCancel(DialogInterface dialog) {
                                                    Intent i0 = new Intent(RunActivity.this, launching.class);
                                                    startActivity(i0);
                                                }
                                            })
                                            .show();
                                }
                                refresh();
                            }
                        }
                    }
                }
            });
        }
    }

    void refresh() {
        bat_RUN.setText(0 + "");
        bat_WKT.setText(0 + "");
        no_Balls.setText(0 + "");
        no_Overs.setText(0 + "");
        bat1R = 0;
        bat1_run.setText(0 + "");
        bat1_ball.setText("(" + 0 + ")");
        bat1B = 0;
        bat2_run.setText(0 + "");
        bat2_ball.setText("(" + 0 + ")");
        bat2R = 0;
        bat2B = 0;
    }
//    void runReqd()
//    {
//        if(Innings==2)
//        {
//            int reqdRun = team1RUN - RUN;
//            int reqdBall = (Values.overs*6) - (Integer.parseInt(no_Overs.getText().toString())*6+Integer.parseInt(no_Balls.getText().toString()));
//            run_2_win.setText("Need "+(reqdRun+1)+" runs from "+reqdBall+" balls");
//        }
//        else
//        {
//            runrate = (RUN/(Integer.parseInt(no_Overs.getText().toString())*6+Integer.parseInt(no_Balls.getText().toString())))*6;
//            double projScore = runrate*(Values.overs);
//            int pS = (int) projScore;
//            run_2_win.setText("Projected Score : "+pS);
//        }
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.run, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bat_sc) {
            // Handle the camera action
            if(Values.inn==1) {
                Intent i1 = new Intent(this, playerlist1.class);
                startActivity(i1);
            }
            else if(Values.inn==2){
                Intent i2 = new Intent(this, playerlist2.class);
                startActivity(i2);
            }
        } else if (id == R.id.nav_runrate) {
            double rr = RUN/((Integer.parseInt(no_Overs.getText().toString())*6)+(Integer.parseInt(no_Balls.getText().toString())))*6;
            Toast.makeText(this,"Run Rate : "+rr,Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
