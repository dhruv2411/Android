package com.example.dipmalyasen.sample1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class launching extends AppCompatActivity {

    Button gotoMatch,gotoStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);

        gotoMatch = (Button) findViewById(R.id.start_match);
        gotoStat = (Button) findViewById(R.id.view_stat);

        gotoMatch.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view)
            {
                Intent i = new Intent(launching.this,teamName.class);
                startActivity(i);
            }
        });
        gotoStat.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                Intent i = new Intent(launching.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
