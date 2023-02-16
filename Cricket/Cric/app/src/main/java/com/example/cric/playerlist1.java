package com.example.cric;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class playerlist1 extends AppCompatActivity {

    ListView lView;
    String[] p = new String[Values.playersNum];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist1);
        lView= (ListView) findViewById(R.id.lv);
        int i=Values.playersNum;
        for(int c=0;c<i;c++)
        {
            p[c]=Values.teamPlayer1[c];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,p);
        lView.setAdapter(adapter);
    }
}
