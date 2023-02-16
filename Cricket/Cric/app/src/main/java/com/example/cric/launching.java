package com.example.cric;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class launching extends AppCompatActivity {

    Button gotoMatch,gotoStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        gotoMatch = (Button) findViewById(R.id.start_match);
        gotoStat = (Button) findViewById(R.id.view_stat);

        gotoMatch.setOnClickListener(view -> {
            Intent i = new Intent(launching.this,teamName.class);
            startActivity(i);
        });
        gotoStat.setOnClickListener(v -> {
            Intent i = new Intent(launching.this,MainActivity.class);
            startActivity(i);
        });
    }
}
