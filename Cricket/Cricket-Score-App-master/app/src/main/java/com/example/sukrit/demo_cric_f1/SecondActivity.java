package com.example.sukrit.demo_cric_f1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sukrit on 4/13/2017.
 */

public class SecondActivity extends AppCompatActivity {

    CardView btnAllMatches,btnFilter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_xml);

        btnAllMatches=(CardView) findViewById(R.id.btnAllMatches);
        btnFilter=(CardView) findViewById(R.id.btnFilter);

        btnAllMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent2=new Intent(SecondActivity.this,AllMatchesActivity.class);
                startActivity(myIntent2);
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent3=new Intent(SecondActivity.this,Filters.class);
                startActivity(myIntent3);
            }
        });



    }
}

