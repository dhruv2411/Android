package com.example.cric;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class teamName extends AppCompatActivity {

    EditText numberOfOver, numberOfPlayers, teamName1, teamName2;
    Button proceedTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_name);

        numberOfOver = (EditText) findViewById(R.id.over_no);
        numberOfPlayers = (EditText) findViewById(R.id.numOfPlayers);
        teamName1 = (EditText) findViewById(R.id.team_1);
        teamName2 = (EditText) findViewById(R.id.team_2);

        proceedTo = (Button) findViewById(R.id.procced);

        proceedTo.setOnClickListener(view -> {
            try {
                if (Integer.parseInt(numberOfPlayers.getText().toString()) < 12 && Integer.parseInt(numberOfPlayers.getText().toString()) > 0) {
                    if (!(teamName1.getText().toString().isEmpty()) && !(teamName2.getText().toString().isEmpty())) {
                        if(Integer.parseInt(numberOfOver.getText().toString())>0 && !(numberOfOver.getText().toString().isEmpty())) {

                            Values.team1=teamName1.getText().toString();
                            Values.team2=teamName2.getText().toString();
                            Values.overs=Integer.parseInt(numberOfOver.getText().toString());
                            Values.ball=0;
                            Values.playersNum=Integer.parseInt(numberOfPlayers.getText().toString());

                            Intent i = new Intent(teamName.this, players.class);
                            i.putExtra("key", (numberOfPlayers.getText().toString()));
                            i.putExtra("key2", (teamName1.getText().toString()));
                            i.putExtra("key3", (teamName2.getText().toString()));
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(teamName.this, "Invalid number of Overs", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(teamName.this, "Team Name is missing", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(teamName.this, "Number of players exceed 11", Toast.LENGTH_LONG).show();
                    numberOfPlayers.setText("");
                }
            } catch (Exception e) {
                Toast.makeText(teamName.this, "Check all the values", Toast.LENGTH_LONG).show();
            }
        });
    }
}
