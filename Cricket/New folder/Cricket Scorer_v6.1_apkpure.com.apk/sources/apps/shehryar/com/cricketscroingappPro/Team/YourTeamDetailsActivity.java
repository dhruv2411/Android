package apps.shehryar.com.cricketscroingappPro.Team;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import java.util.ArrayList;

public class YourTeamDetailsActivity extends Activity implements View.OnClickListener {
    Button baddanotherteam;
    Button bsubmit;
    ArrayList<Team_players> current_team_players;
    DBHelper dbhelper;
    EditText[] edplayers;
    EditText edyourteam;
    Team_players obj;
    Spinner[] spinners;
    ArrayList<Team_players> team_players;
    ArrayList<String> yourplayers;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_your_team_details);
        this.dbhelper = new DBHelper(this);
        this.obj = getYourTeam();
        this.team_players = (ArrayList) getIntent().getSerializableExtra("team_players");
        this.current_team_players = new ArrayList<>();
        this.edplayers = new EditText[15];
        this.spinners = new Spinner[15];
        initializeViews();
        getWindow().setSoftInputMode(2);
    }

    /* access modifiers changed from: package-private */
    public void initializeViews() {
        this.edyourteam = (EditText) findViewById(R.id.edyourteamname);
        this.edplayers[0] = (EditText) findViewById(R.id.edp1);
        this.edplayers[1] = (EditText) findViewById(R.id.edp2);
        this.edplayers[2] = (EditText) findViewById(R.id.edp3);
        this.edplayers[3] = (EditText) findViewById(R.id.edp4);
        this.edplayers[4] = (EditText) findViewById(R.id.edp5);
        this.edplayers[5] = (EditText) findViewById(R.id.edp6);
        this.edplayers[6] = (EditText) findViewById(R.id.edp7);
        this.edplayers[7] = (EditText) findViewById(R.id.edp8);
        this.edplayers[8] = (EditText) findViewById(R.id.edp9);
        this.edplayers[9] = (EditText) findViewById(R.id.edp10);
        this.edplayers[10] = (EditText) findViewById(R.id.edp11);
        this.edplayers[11] = (EditText) findViewById(R.id.edp12);
        this.edplayers[12] = (EditText) findViewById(R.id.edp13);
        this.edplayers[13] = (EditText) findViewById(R.id.edp14);
        this.edplayers[14] = (EditText) findViewById(R.id.edp15);
        this.bsubmit = (Button) findViewById(R.id.bupdatedetails);
        this.bsubmit.setOnClickListener(this);
        this.edyourteam.setText(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("teamname", ""));
        showvalues();
    }

    private void showvalues() {
        if (!getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("teamname", "").isEmpty() && this.obj != null) {
            for (int i = 0; i < this.obj.getPlayers().size(); i++) {
                if (!(this.obj.getPlayers().get(i) == null || this.edplayers[i] == null)) {
                    this.edplayers[i].setText(this.obj.getPlayers().get(i));
                }
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bupdatedetails) {
            handleSubmit();
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getYourPlayers(String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 0;
        if (str.isEmpty()) {
            while (i < 15) {
                arrayList.add("");
                i++;
            }
        } else {
            while (i < this.team_players.size()) {
                if (this.team_players.get(i).getTeamname().equals(str)) {
                    arrayList = this.team_players.get(i).getPlayers();
                }
                i++;
            }
        }
        return arrayList;
    }

    private void addAnotherTeam() {
        startActivity(new Intent(this, OtherTeamPlayersActivity.class));
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> getYourplayers() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (this.edplayers[i].getText().toString().isEmpty()) {
                arrayList.add(" ");
            } else {
                arrayList.add(this.edplayers[i].getText().toString());
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public void handleSubmit() {
        if (this.edyourteam.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Enter the name of team", 0).show();
        } else if (getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("teamname", "").isEmpty()) {
            this.dbhelper.insertTeamPlayers(this.edyourteam.getText().toString(), getYourplayers(), "yours");
            SharedPreferences.Editor edit = getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
            edit.putString("teamname", this.edyourteam.getText().toString());
            edit.commit();
            setResult(2);
            Toast.makeText(this, "Team added", 0).show();
            finish();
        } else {
            this.dbhelper.UpdateTeamPlayers(getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("teamname", ""), this.edyourteam.getText().toString(), getYourplayers(), "yours");
            SharedPreferences.Editor edit2 = getSharedPreferences(DBHelper.TABLE_TEAM, 0).edit();
            edit2.putString("teamname", this.edyourteam.getText().toString());
            edit2.commit();
            Toast.makeText(this, "Team Updated", 0).show();
            setResult(2);
            finish();
        }
    }

    class getTeams extends AsyncTask {
        getTeams() {
        }

        /* access modifiers changed from: protected */
        public Object doInBackground(Object[] objArr) {
            YourTeamDetailsActivity.this.current_team_players = YourTeamDetailsActivity.this.dbhelper.getAllTeamPlayers();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public Team_players getYourTeam() {
        Team_players team_players2 = new Team_players();
        String string = getSharedPreferences(DBHelper.TABLE_TEAM, 0).getString("teamname", "");
        return !string.isEmpty() ? this.dbhelper.getYourTeamPlayers(string) : team_players2;
    }
}
