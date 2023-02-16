package com.example.dipmalyasen.sample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
