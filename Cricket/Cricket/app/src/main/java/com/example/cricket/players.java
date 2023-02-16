package com.example.cricket;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cricket.R;

public class players extends AppCompatActivity {
    int numberOfPlayers;
    Button submit;
    TextView tN1, tN2;
    static int token = 0;
    String temp, teamName1, teamName2;
    ArrayAdapter<String> pl1;

    private SQLiteDatabase cs;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)

    private void createDatabase() {
        cs = openOrCreateDatabase("playersDB",MODE_PRIVATE, null);
        String query = "CREATE TABLE IF NOT EXISTS PLAYERS(ID INTEGER,NAME VARCHAR,OUT VARCHAR,RUN INTEGER,BALL INTEGER,OVER INTEGER,RUNGIVEN INTEGER,WICKET INTEGER,CATCH INTEGER);";
        cs.execSQL(query);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        Intent intent = getIntent();
        temp = intent.getStringExtra("key");
        teamName1 = intent.getStringExtra("key2");
        teamName2 = intent.getStringExtra("key3");
        numberOfPlayers = Integer.parseInt(temp);

        LinearLayout llview = (LinearLayout) findViewById(R.id.activity_players);


        final EditText[] name = new EditText[numberOfPlayers];

        for (int count = 0; count < numberOfPlayers; count++) {
            name[count] = new EditText(this);
            name[count].setId(count + 100);
            name[count].setHint("Enter name");
            name[count].setTextSize(18);
            name[count].setSelectAllOnFocus(true);
            name[count].setSingleLine();
            name[count].setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
            name[count].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llview.addView(name[count]);
        }
        Button submit = new Button(this);
        submit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        submit.setText("Done");
        llview.addView(submit);


//        for (int i = 0; i < numberOfPlayers; i++) {
//            if ((name[i].getText().toString().isEmpty()))
//                token = 0;
//            else
//                token += 1;
//        }
        submit.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                // if (token == (numberOfPlayers)) {


                //}else
                //Toast.makeText(players.this, "Fill up all Players", Toast.LENGTH_LONG).show();
                createDatabase();
                for (int i = 0; i <numberOfPlayers; i++)
                {
                    String insert_query = "INSERT INTO PLAYERS VALUES("+(i+1)+",'"+(name[i].getText().toString())+"','"+null+"',"+0+","+0+","+0+","+0+","+0+","+0+");";
                    cs.execSQL(insert_query);
                    Values.teamPlayer1[i]=name[i].getText().toString();
                    //Values.play1.add(name[i].getText().toString());
                }


                Intent in = new Intent(players.this, players2.class);
                in.putExtra("number", temp);
                startActivity(in);
                finish();

//                for (int i = 0; i < numberOfPlayers; i++) {
//                    Toast.makeText(players.this,name[i].getText().toString(), Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

}
