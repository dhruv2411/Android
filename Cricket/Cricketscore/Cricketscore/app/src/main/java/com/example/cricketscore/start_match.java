package com.example.cricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class start_match extends AppCompatActivity {

    public static int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_match);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        count = 0;
        final DatabaseHandler db = new DatabaseHandler(this);

        List<String> tour = db.getAllTournament();
        //final List<TeamModel> list = new ArrayList<TeamModel>();
        //for(String temp : tour)
          //  list.add(new TeamModel(temp));
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tour));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tname = (String) listView.getItemAtPosition(i);
                System.out.println(tname+"shreyas");
                Intent intent = new Intent(start_match.this , new_activity.class);
                intent.putExtra("team_name", tname);
                startActivity(intent);
            }
        });


        /*listView.setAdapter(new TeamArrayAdapter(this , list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TeamModel temp = (TeamModel) listView.getItemAtPosition(position);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.CheckBox01);
                System.out.println(count);
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                } else {
                    if (count == 2) {
                        Snackbar.make(view, "You can select only 2 teams", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    } else {
                        checkBox.setChecked(true);
                    }
                }
            }
        });*/
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });    }
}
