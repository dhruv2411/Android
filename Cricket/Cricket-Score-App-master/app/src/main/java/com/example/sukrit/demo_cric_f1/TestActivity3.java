//package com.example.sukrit.demo_cric_f1;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.widget.TextView;
//
//public class TestActivity extends AppCompatActivity {
//
//    TextView test_team1;
//    TextView test_team2;
//    TextView test_score;
//    TextView test_type;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_test);
//
//        test_score=(TextView)findViewById(R.id.score);
//        test_type=(TextView)findViewById(R.id.type);
//
//        String team1=getIntent().getStringExtra("team1");
//        String team2=getIntent().getStringExtra("team2");
//        String score=getIntent().getStringExtra("score");
//        String type=getIntent().getStringExtra("type");
//
////        Log.d("TEAMSSSS", "onCreate: "+team1);
////        Log.d("TEAMSSSS", "onCreate: "+team2);
////        Log.d("TEAMSSSS", "onCreate: "+score);
//
//        test_score.setText(score);
//        test_type.setText(type);
//    }
//}
package com.example.sukrit.demo_cric_f1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TestActivity3 extends AppCompatActivity {

   TextView date;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        date=(TextView)findViewById(R.id.date);
        name=(TextView)findViewById(R.id.name);

        String getdateCal=getIntent().getStringExtra("date");
        String getNameCal=getIntent().getStringExtra("name");

        date.setText(getdateCal);
        name.setText(getNameCal);
    }
}
