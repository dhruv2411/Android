package com.shreyaslakhe.cricin;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import java.util.Locale;

import java.util.Calendar;

public class add_tournament extends AppCompatActivity {
    private EditText start_date;
    private EditText end_date, tournament_name;
    private Calendar myCalendar;
    private int box;
    private int START = 1;
    private int END = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tournament);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DatePicker dp = new DatePicker(this.getApplicationContext());

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
        start_date = (EditText) findViewById(R.id.start_Date);
        end_date = (EditText) findViewById(R.id.end_date);

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(add_tournament.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                box = START;
                start_date.requestFocus();
            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(add_tournament.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                box = END;
            }
        });

        tournament_name = (EditText)findViewById(R.id.tournament_name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText name = (EditText) findViewById(R.id.tournament_name);
                EditText venue = (EditText) findViewById(R.id.venue);
                EditText matches = (EditText)findViewById(R.id.matches_in_tournament);

                Tournament tournament = new Tournament();

                if(!name.getText().toString().equals("") && !matches.getText().toString().equals("") && !venue.getText().toString().equals("")
                        && !start_date.getText().toString().equals("") && !end_date.getText().toString().equals("")) {
                    tournament.set_start_date(start_date.getText().toString());
                    tournament.set_venue(venue.getText().toString());
                    tournament.set_matches(Integer.parseInt(matches.getText().toString()));
                    tournament.set_name(name.getText().toString());
                    tournament.set_end_date(end_date.getText().toString());

                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.addTournament(tournament);

                    Intent ret = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(ret);
                }
                else{
                    Snackbar.make(view, "Please, Fill all the Fields.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });

    }
    private void updateLabel() {

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if(box == START)
            start_date.setText(sdf.format(myCalendar.getTime()));
        else if(box == END)
            end_date.setText(sdf.format(myCalendar.getTime()));
    }

}
