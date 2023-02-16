package com.example.dipmalyasen.sample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class playerlist2 extends AppCompatActivity {
    ListView lView2;
    String[] p1 = new String[Values.playersNum];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playerlist2);
        lView2= (ListView) findViewById(R.id.lv2);
        int i=Values.playersNum;
        for(int c=0;c<i;c++)
        {
            p1[c]=Values.teamPlayer2[c];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,p1);
        lView2.setAdapter(adapter);
    }
}
