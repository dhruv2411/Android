package com.example.cricketscore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_team extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final com.shreyaslakhe.cricin.DatabaseHandler db = new com.shreyaslakhe.cricin.DatabaseHandler(getApplicationContext());
        Button b = (Button)findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ret = new Intent(getApplicationContext(), MainActivity.class);
                EditText name = (EditText) findViewById(R.id.team_name);
                EditText matches = (EditText) findViewById(R.id.matches);
                EditText won = (EditText) findViewById(R.id.won);
                EditText lost = (EditText) findViewById(R.id.lost);
                            if (name.getText().toString().equals("")) {
                                Snackbar.make(view, "Team Name is Mandatory", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            } else {
                                Team team = new Team();
                                team.set_name(name.getText().toString());
                                if (matches.getText().toString().length() > 0)
                                    team.set_matches(Integer.parseInt(matches.getText().toString()));
                                if (won.getText().toString().length() > 0)
                                    team.set_won(Integer.parseInt(won.getText().toString()));
                                if (lost.getText().toString().length() > 0)
                                    team.set_lost(Integer.parseInt(lost.getText().toString()));
                                team.set_no_result(team.get_matches() - (team.get_won() + team.get_lost()));

                                db.addTeam(team);
                        }}});
                }
}
