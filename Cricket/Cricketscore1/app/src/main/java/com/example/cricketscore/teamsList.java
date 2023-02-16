package com.example.cricketscore;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class teamsList extends AppCompatActivity {

    public static int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        count = 0;

        final DatabaseHandler db = new DatabaseHandler(this);

        List<String> tnames = db.getAllTeams();
        final List<TeamModel> list = new ArrayList<TeamModel>();
        for(String temp : tnames)
          list.add(new TeamModel(temp));

        final ListView listView = (ListView) findViewById(R.id.listView03);
        final TeamArrayAdapter listAdapter = new TeamArrayAdapter(this, list);
        listView.setAdapter(listAdapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                TeamModel temp = (TeamModel) listView.getItemAtPosition(position);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.CheckBox01);
              /*  if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                } else {
                        checkBox.setChecked(true);
                }*/
            }
        });

        Bundle bundle = getIntent().getExtras();
        final String tname = bundle.getString("tournament_name");
        Button teamSavebtn = (Button) findViewById(R.id.saveTeamsBtn);
        teamSavebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = listView.getCheckedItemPositions();
                System.out.println("this not working");
                if(checked == null)
                    System.out.println("trolled");
                int len = listAdapter.getCount();
                for (int i = 0; i < len; i++) {
                    System.out.println("may be this");
                    TeamModel t = listAdapter.getItem(i);
                    if (t.isSelected()) {
                        System.out.println("this is working");
                        TeamModel temp2 = (TeamModel) listView.getItemAtPosition(i);
                        db.addtoParticipate(0, tname, t.getName());
                    }
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
