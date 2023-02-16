package com.example.sukrit.demo_cric_f1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;


/**
 * Created by Sukrit on 4/13/2017.
 */

public class AllMatchesActivity extends SecondActivity {

    CardView btnUpcoming, btnCurrent, btnCalendar;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmatches);

        btnUpcoming = (CardView) findViewById(R.id.btnUpcoming);
        btnCurrent = (CardView) findViewById(R.id.btnCurrent);
        btnCalendar=(CardView) findViewById(R.id.btnCalendar);
//        btnRecent = (Button) findViewById(R.id.btnRecent);

        btnUpcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//            
                Intent myIntent2 = new Intent(AllMatchesActivity.this, Upcoming.class);
                startActivity(myIntent2);
            }
        } );
        btnCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recent.DownloadTask downloadTask=new Recent.DownloadTask(getApplicationContext());
                downloadTask.execute("http://cricapi.com/api/matches?apikey=CjCdXi1JuqVuqOPFGRGfG5RtGu23");
                Intent i2=new Intent(AllMatchesActivity.this,Recent.class);
                startActivity(i2);

            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3=new Intent(AllMatchesActivity.this,Calendar.class);
                startActivity(i3);
            }
        });
   }
}