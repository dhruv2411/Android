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

public class TestActivity4 extends AppCompatActivity {

    TextView test_team1;
    TextView test_team2;
    TextView test_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);

        test_team1=(TextView)findViewById(R.id.team1);
        test_team2=(TextView)findViewById(R.id.team2);
        test_score=(TextView)findViewById(R.id.score);

        String team1=getIntent().getStringExtra("team1");
        String team2=getIntent().getStringExtra("team2");
        String score=getIntent().getStringExtra("score");

        test_team1.setText(team1);
        test_team2.setText(team2);
        if(score==null)
        {
            test_score.setText("Score cannot be shown ..!");
        }
        else {
            test_score.setText(score);
        }
    }
}
