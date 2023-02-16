package apps.shehryar.com.cricketscroingappPro.Match;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHistoryActivity;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlersHisotryActivity;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class PreviousMatchesListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DBHelper dbHelper;
    int index;
    Intent intent;
    ListView listView;
    ArrayList<Match> matches;
    RecyclerView recyclerView;
    ArrayList<Team> team1scores;
    ArrayList<Team> team1stats;
    ArrayList<Team> team2scores;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamNames;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_previous_matches_list);
        this.recyclerView = (RecyclerView) findViewById(R.id.batsmanrecyclerview);
        getSupportActionBar().setTitle((CharSequence) "Previous Matches");
        this.dbHelper = new DBHelper(this);
        this.team1scores = new ArrayList<>();
        this.team1stats = new ArrayList<>();
        this.team2stats = new ArrayList<>();
        new DataLoader().execute(new Integer[0]);
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        getMenuInflater().inflate(R.menu.match_menu, contextMenu);
    }

    public boolean onContextItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.your_team_batting /*2131755778*/:
                this.intent = new Intent(this, BatsmanHistoryActivity.class);
                this.intent.putExtra(DBHelper.TABLE_MATCH, this.matches.get(this.index));
                this.intent.putExtra(DBHelper.TABLE_TEAM, this.team1stats.get(this.index));
                this.intent.putExtra("team2name", this.team2stats.get(this.index).getName());
                this.intent.putExtra("index", this.index);
                this.intent.putExtra("previousbat", true);
                startActivity(this.intent);
                break;
            case R.id.opp_team_bowling /*2131755779*/:
                Intent intent2 = new Intent(this, BowlersHisotryActivity.class);
                intent2.putExtra("index", this.index);
                intent2.putExtra("fullact", true);
                intent2.putExtra("pre_bowling", true);
                intent2.putExtra(DBHelper.TABLE_MATCH, this.matches.get(this.index));
                intent2.putExtra(DBHelper.TABLE_TEAM, this.team1stats.get(this.index));
                intent2.putExtra("code", 99);
                startActivity(intent2);
                break;
            case R.id.your_team_bowling /*2131755780*/:
                Intent intent3 = new Intent(this, BowlersHisotryActivity.class);
                intent3.putExtra("index", this.index);
                intent3.putExtra("pre_bowling", true);
                intent3.putExtra("fullact", true);
                intent3.putExtra(DBHelper.TABLE_MATCH, this.matches.get(this.index));
                intent3.putExtra(DBHelper.TABLE_TEAM, this.team1stats.get(this.index));
                startActivity(intent3);
                break;
            case R.id.opp_team_batting /*2131755781*/:
                this.intent = new Intent(this, BatsmanHistoryActivity.class);
                this.intent.putExtra(DBHelper.TABLE_MATCH, this.matches.get(this.index));
                this.intent.putExtra(DBHelper.TABLE_TEAM, this.team2stats.get(this.index));
                this.intent.putExtra("index", this.index);
                this.intent.putExtra("code", 99);
                this.intent.putExtra("previousbat", true);
                this.intent.putExtra("team2name", this.team2stats.get(this.index).getName());
                startActivity(this.intent);
                break;
        }
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intent intent2 = new Intent(this, Previous_Match_Result.class);
        int i2 = i + i;
        intent2.putExtra("team1name", this.teamNames.get(i2).getName());
        intent2.putExtra("team2name", this.teamNames.get(i2 + 1).getName());
        intent2.putExtra("team1stats", this.team1stats.get(i));
        intent2.putExtra("team2stats", this.team2stats.get(i));
        intent2.putExtra(DBHelper.TABLE_MATCH, this.matches.get(i));
        intent2.putExtra("index", i);
        startActivity(intent2);
    }

    class DataLoader extends AsyncTask<Integer, Team, Boolean> {
        DataLoader() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Integer... numArr) {
            PreviousMatchesListActivity.this.matches = PreviousMatchesListActivity.this.dbHelper.getMatches(numArr[0].intValue());
            PreviousMatchesListActivity.this.teamNames = PreviousMatchesListActivity.this.dbHelper.getTeam();
            PreviousMatchesListActivity.this.team1scores = PreviousMatchesListActivity.this.dbHelper.getTeam1Stats(PreviousMatchesListActivity.this.matches);
            PreviousMatchesListActivity.this.team2scores = PreviousMatchesListActivity.this.dbHelper.getTeam2Stats(PreviousMatchesListActivity.this.matches);
            for (int i = 0; i < PreviousMatchesListActivity.this.matches.size(); i++) {
                PreviousMatchesListActivity.this.team1stats.add(PreviousMatchesListActivity.this.dbHelper.getTeam1Stats(PreviousMatchesListActivity.this.matches.get(i).getMatchID(), PreviousMatchesListActivity.this.matches.get(i).getTeam_Yours_id()));
            }
            for (int i2 = 0; i2 < PreviousMatchesListActivity.this.matches.size(); i2++) {
                PreviousMatchesListActivity.this.team2stats.add(PreviousMatchesListActivity.this.dbHelper.getTeam1Stats(PreviousMatchesListActivity.this.matches.get(i2).getMatchID(), PreviousMatchesListActivity.this.matches.get(i2).getTeam_opp_id()));
            }
            return true;
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Team... teamArr) {
            super.onProgressUpdate(teamArr);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            PreviousMatchesListActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(PreviousMatchesListActivity.this.getApplicationContext()));
            PreviousMatchesListActivity.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
            PreviousMatchesListActivity.this.recyclerView.setAdapter(new PreviousMatchesListAdapter(PreviousMatchesListActivity.this, PreviousMatchesListActivity.this, PreviousMatchesListActivity.this.matches));
        }
    }
}
