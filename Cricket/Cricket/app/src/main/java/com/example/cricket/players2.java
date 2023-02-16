package com.example.cricket;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dipmalyasen.sample1.R;

public class players2 extends AppCompatActivity {
    int numberOfPlayers;
    Button submit;

    private SQLiteDatabase cs;

    private void createDatabase() {
        cs = openOrCreateDatabase("playersDB",MODE_PRIVATE, null);
        String query = "CREATE TABLE IF NOT EXISTS PLAYERS2(ID INTEGER,NAME VARCHAR,OUT VARCHAR,RUN INTEGER,BALL INTEGER,OVER INTEGER,RUNGIVEN INTEGER,WICKET INTEGER,CATCH INTEGER);";
        cs.execSQL(query);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players2);

        Intent i2 = getIntent();
        final int numberOfPlayers = Integer.parseInt(i2.getStringExtra("number"));

        LinearLayout llview = (LinearLayout)findViewById(R.id.activity_players2);

        final EditText[] name = new EditText[numberOfPlayers];

        for(int count=0;count<numberOfPlayers;count++)
        {
            name[count] = new EditText(this);
            name[count].setId(count+100);
            name[count].setHint("Enter name");
            name[count].setTextSize(18);
            name[count].setSelectAllOnFocus(true);
            name[count].setSingleLine();
            name[count].setFilters( new InputFilter[] { new InputFilter.LengthFilter(15) } );
            name[count].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llview.addView(name[count]);
        }
        Button submit = new Button(this);
        submit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        submit.setText("Done");
        llview.addView(submit);

        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                createDatabase();
                for (int i = 0; i <numberOfPlayers; i++)
                {
                    String insert_query = "INSERT INTO PLAYERS2 VALUES("+(i+1)+",'"+(name[i].getText().toString())+"','"+null+"',"+0+","+0+","+0+","+0+","+0+","+0+");";
                    cs.execSQL(insert_query);
                    Values.teamPlayer2[i]=name[i].getText().toString();
                }

                Intent in = new Intent(players2.this,customDialog.class);
                startActivity(in);
                finish();

            }
        });
    }
}
