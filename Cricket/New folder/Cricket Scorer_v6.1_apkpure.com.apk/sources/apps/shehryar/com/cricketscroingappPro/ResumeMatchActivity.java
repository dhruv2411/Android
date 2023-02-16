package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.PreviousUnfinishedMatchesListAdapter;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class ResumeMatchActivity extends AppCompatActivity {
    Context context;
    DBHelper dbHelper;
    ArrayList<Match> finalMatches;
    int index;
    Intent intent;
    ListView listView;
    ArrayList<Match> matches;
    TextView noMatches;
    RecyclerView recyclerView;
    ArrayList<Team> team1scores;
    ArrayList<Team> team1stats;
    ArrayList<Team> team2scores;
    ArrayList<Team> team2stats;
    ArrayList<Team> teamNames;
    ArrayList<Team> teams;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_resume_match);
        this.context = this;
        this.recyclerView = (RecyclerView) findViewById(R.id.batsmanrecyclerview);
        this.noMatches = (TextView) findViewById(R.id.no_matches_text);
        this.dbHelper = new DBHelper(this);
        this.team1scores = new ArrayList<>();
        this.team1stats = new ArrayList<>();
        this.team2stats = new ArrayList<>();
        this.teams = (ArrayList) getIntent().getSerializableExtra("teams");
        getSupportActionBar().setTitle((CharSequence) "Resume Match");
        new DataLoader().execute(new Integer[0]);
    }

    class DataLoader extends AsyncTask<Integer, Team, Boolean> {
        DataLoader() {
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Integer... numArr) {
            ResumeMatchActivity.this.matches = ResumeMatchActivity.this.dbHelper.getMatches(4);
            try {
                Log.i("match Team ids", ResumeMatchActivity.this.matches.get(0).getTeam_Yours_id() + " " + ResumeMatchActivity.this.matches.get(0).getTeam_opp_id() + " " + ResumeMatchActivity.this.matches.get(0).getTeam_Yours_id2() + " " + ResumeMatchActivity.this.matches.get(0).getTeam_opp_id2());
            } catch (Exception e) {
                e.printStackTrace();
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
            ResumeMatchActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(ResumeMatchActivity.this.context.getApplicationContext()));
            ResumeMatchActivity.this.recyclerView.setItemAnimator(new DefaultItemAnimator());
            PreviousUnfinishedMatchesListAdapter previousUnfinishedMatchesListAdapter = new PreviousUnfinishedMatchesListAdapter(ResumeMatchActivity.this.context, ResumeMatchActivity.this.matches, ResumeMatchActivity.this.teams);
            if (ResumeMatchActivity.this.matches.isEmpty()) {
                ResumeMatchActivity.this.noMatches.setVisibility(0);
                ResumeMatchActivity.this.noMatches.setText("No Match to resume");
            } else {
                ResumeMatchActivity.this.noMatches.setVisibility(8);
            }
            ResumeMatchActivity.this.recyclerView.setAdapter(previousUnfinishedMatchesListAdapter);
        }
    }

    private void deleteMatch(ArrayList<Match> arrayList, long j, int i) {
        arrayList.remove(Long.valueOf(arrayList.get(i).getTeam_Yours_id()));
    }
}
