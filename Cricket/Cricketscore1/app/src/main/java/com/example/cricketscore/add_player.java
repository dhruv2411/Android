package com.example.cricketscore;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add_player extends AppCompatActivity {

    private int year, month, day;
    private DatePicker dp;
    private EditText dob, name, matches;
    private EditText runs, wickets, catches;
    private EditText highest, n_fifty, n_hund;
    private EditText bat_avg, bowl_avg;
    static final int DATE_DIALOG_ID = 999;
    private Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DatabaseHandler db = new DatabaseHandler(this);
        dp = new DatePicker(this.getApplicationContext());

        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dob = (EditText)findViewById(R.id.editText11);
        name = (EditText)findViewById(R.id.editText);
        matches = (EditText)findViewById(R.id.editText2);
        runs = (EditText) findViewById(R.id.editText3);
        wickets = (EditText) findViewById(R.id.editText4);
        catches = (EditText) findViewById(R.id.editText5);
        highest = (EditText) findViewById(R.id.editText6);
        n_fifty = (EditText) findViewById(R.id.editText7);
        n_hund = (EditText) findViewById(R.id.editText8);
        bat_avg = (EditText) findViewById(R.id.editText9);
        bowl_avg = (EditText) findViewById(R.id.editText10);

        Button save = (Button) findViewById(R.id.btnSave2);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                Player player = db.getPlayer("Shreyas");
               name.setText(player.get_name());
                Snackbar.make(view, player.get_name(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_player.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }

            ;

        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View view) {
                                       if(name.getText().toString().length() == 0) {
                                           Snackbar.make(view, "Name is compulsory", Snackbar.LENGTH_LONG)
                                                   .setAction("Action", null).show();
                                       }

                                       Player player = new Player();
                                       player.set_name(name.getText().toString());
                                       player.set_date_of_birth(dob.getText().toString());
                                       player.set_no_of_matches(Integer.parseInt(matches.getText().toString()));
                                       player.set_runs(Integer.parseInt(runs.getText().toString()));
                                       player.set_wickets(Integer.parseInt(wickets.getText().toString()));
                                       player.set_catches(Integer.parseInt(catches.getText().toString()));
                                       player.set_fifty(Integer.parseInt(n_fifty.getText().toString()));
                                       player.set_hundred(Integer.parseInt(n_hund.getText().toString()));
                                       player.set_bowling_average(Integer.parseInt(bowl_avg.getText().toString()));
                                       player.set_average(Integer.parseInt(bat_avg.getText().toString()));
                                       db.addPlayer(player);
                                   }
                               }

        );

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }
}
