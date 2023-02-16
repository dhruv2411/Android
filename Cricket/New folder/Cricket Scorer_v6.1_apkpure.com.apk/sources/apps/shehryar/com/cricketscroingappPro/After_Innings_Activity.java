package apps.shehryar.com.cricketscroingappPro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.BatsmanHistoryActivity;
import apps.shehryar.com.cricketscroingappPro.Batsman.FragmentBatsmanHistoryRecyclerView;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlerViewsUpdater;
import apps.shehryar.com.cricketscroingappPro.Bowler.BowlersHisotryActivity;
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Match.ResumeMatch;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.SharedPrefsHelper;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import java.util.ArrayList;

public class After_Innings_Activity extends AppCompatActivity implements View.OnClickListener {
    Button bStartFollowOn;
    Button bstartnextinnings;
    DBHelper dbHelper;
    ArrayList<FallOfWickets> fallOfWicketses;
    private InterstitialAd mInterstitialAd;
    Match match;
    boolean resume_check = false;
    Team team1;
    Team team2;
    int totalovers;
    TextView tvscore;
    TextView tvteamextra;
    TextView tvteamname;
    TextView tvteamovers;
    TextView tvteamscore;
    TextView tvwickets;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.after_innings_activity_new);
        this.dbHelper = new DBHelper(this);
        getSupportActionBar().setTitle((CharSequence) "First Innings Summary");
        this.tvscore = (TextView) findViewById(R.id.tvscore);
        this.tvwickets = (TextView) findViewById(R.id.tvwickets);
        this.bstartnextinnings = (Button) findViewById(R.id.bstartnextinings);
        this.bStartFollowOn = (Button) findViewById(R.id.bstartFollowOn);
        try {
            FontProvider.setEuroStileFont(this, this.bstartnextinnings);
            FontProvider.setEuroStileFont(this, this.bStartFollowOn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.tvteamname = (TextView) findViewById(R.id.tvinnteamname);
        this.tvteamscore = (TextView) findViewById(R.id.tvscore);
        this.tvteamovers = (TextView) findViewById(R.id.tvinnovers);
        this.bstartnextinnings.setOnClickListener(this);
        this.tvteamextra = (TextView) findViewById(R.id.tvextras);
        this.match = (Match) getIntent().getSerializableExtra(DBHelper.TABLE_MATCH);
        this.team1 = this.match.getBattingTeam();
        this.team2 = this.match.getBowlingTeam();
        this.match.switchInnings();
        if (!this.match.thirdInnings) {
            this.bStartFollowOn.setVisibility(8);
        }
        this.bStartFollowOn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                After_Innings_Activity.this.match.setFollowedOn(1);
                After_Innings_Activity.this.match.swapTeams();
                long team_Yours_id2 = After_Innings_Activity.this.match.getTeam_Yours_id2();
                After_Innings_Activity.this.match.setTeam_Yours_id2(After_Innings_Activity.this.match.getTeam_opp_id2());
                After_Innings_Activity.this.match.setTeam_opp_id2(team_Yours_id2);
                After_Innings_Activity.this.dbHelper.updateMatch(After_Innings_Activity.this.match);
                After_Innings_Activity.this.startnextActivity();
            }
        });
        this.resume_check = getIntent().getBooleanExtra("resume_check", false);
        this.totalovers = this.match.getOvers();
        this.fallOfWicketses = (ArrayList) getIntent().getSerializableExtra(DBHelper.TABLE_FALL_OF_WICKETS);
        if (!this.match.isTestMatch) {
            TextView textView = this.tvteamovers;
            textView.setText("(" + this.team1.getOversPlayed() + "." + this.team1.getOverballs() + "/" + this.match.getOvers() + ")");
        } else {
            TextView textView2 = this.tvteamovers;
            textView2.setText("(" + this.team1.getOversPlayed() + "." + this.team1.getOverballs() + ")");
        }
        this.tvteamname.setText(this.team1.getName());
        TextView textView3 = this.tvteamextra;
        textView3.setText("Extras:" + this.team1.getExtras() + "");
        FragmentBatsmanHistoryRecyclerView fragmentBatsmanHistoryRecyclerView = new FragmentBatsmanHistoryRecyclerView();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("batsmen", this.team1.batsmans_list);
        bundle2.putSerializable(DBHelper.TABLE_MATCH, this.match);
        fragmentBatsmanHistoryRecyclerView.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add((int) R.id.batsman_recycler_view_fragment, (Fragment) fragmentBatsmanHistoryRecyclerView).commit();
        BowlerViewsUpdater.showBowlerRecyclerViewFragment(getSupportFragmentManager(), this.team2.bowlers_list, (Dialog_Custom_ListView_Adapter.LinearLayoutClickLister) null, this.team1.overs_list, this.match);
        TextView textView4 = this.tvscore;
        textView4.setText(this.team1.getScore() + "");
        TextView textView5 = this.tvwickets;
        textView5.setText(this.team1.getWickets() + "");
        this.tvscore.setOnClickListener(this);
        this.tvteamname.setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        saveDataInSharedPrefs();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.after_innings_menu, menu);
        return true;
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    /* access modifiers changed from: package-private */
    public void initializeAd() {
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-7751307421283386/1527294159");
        if (!this.mInterstitialAd.isLoading() && !this.mInterstitialAd.isLoaded()) {
            this.mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }
        requestNewInterstitial();
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                After_Innings_Activity.this.startnextActivity();
            }
        });
    }

    /* access modifiers changed from: private */
    public void startnextActivity() {
        Intent intent = new Intent(this, PlayersInputActivity.class);
        intent.putExtra("team1", this.team1);
        intent.putExtra("team2", this.team2);
        intent.putExtra("code", 99);
        intent.putExtra(DBHelper.TABLE_OVERS, this.totalovers + "");
        intent.putExtra("resume_check", true);
        intent.putExtra(DBHelper.TABLE_MATCH, this.match);
        startActivity(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.viewovershistory /*2131755739*/:
                Intent intent = new Intent(this, OverHistoryActivity.class);
                intent.putExtra(DBHelper.TABLE_TEAM, this.team1);
                startActivity(intent);
                return true;
            case R.id.viewfallofwickets /*2131755740*/:
                Intent intent2 = new Intent(this, FallOfWicketsActivity.class);
                intent2.putExtra("wickets", this.team1.fallOfWicketses);
                startActivity(intent2);
                return true;
            default:
                return true;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvscore /*2131755257*/:
            case R.id.tvinnteamname /*2131755340*/:
                AlertDialogBuilder.showTeamDetailsDialog(this.match, this.team1, this);
                return;
            case R.id.bbatsmanhistory /*2131755346*/:
                Intent intent = new Intent(this, BatsmanHistoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DBHelper.TABLE_TEAM, this.team1);
                bundle.putSerializable(DBHelper.TABLE_MATCH, this.match);
                intent.putExtras(bundle);
                startActivity(intent);
                return;
            case R.id.bstartnextinings /*2131755420*/:
                if (this.match.thirdInnings && this.match.followedOn) {
                    long team_Yours_id2 = this.match.getTeam_Yours_id2();
                    this.match.setTeam_Yours_id2(this.match.getTeam_opp_id2());
                    this.match.setTeam_opp_id2(team_Yours_id2);
                    this.match.swapTeams();
                    this.dbHelper.updateMatch(this.match);
                }
                startnextActivity();
                return;
            case R.id.viewbowlershistory /*2131755759*/:
                Intent intent2 = new Intent(this, BowlersHisotryActivity.class);
                intent2.putExtra(DBHelper.TABLE_TEAM, this.team2);
                intent2.putExtra("fullact", true);
                intent2.putExtra(DBHelper.TABLE_OVERS, this.team1.overs_list);
                startActivity(intent2);
                return;
            default:
                return;
        }
    }

    public void onBackPressed() {
        final AlertDialog create = new AlertDialog.Builder(this).setTitle(getResources().getString(R.string.exit)).setMessage(getResources().getString(R.string.exit_message)).setPositiveButton("Yes", (DialogInterface.OnClickListener) null).setNegativeButton("No", (DialogInterface.OnClickListener) null).create();
        create.setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                Button button = create.getButton(-1);
                Button button2 = create.getButton(-2);
                button.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        After_Innings_Activity.this.saveDataInSharedPrefs();
                        After_Innings_Activity.this.finishAffinity();
                        System.exit(0);
                    }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        create.dismiss();
                    }
                });
            }
        });
        create.show();
    }

    /* access modifiers changed from: private */
    public void saveDataInSharedPrefs() {
        ResumeMatch resumeMatch = new ResumeMatch(this.match.getAfterInnings(), "", -1, "");
        Log.i("match after innings ", this.match.getAfterInnings() + " ");
        SharedPrefsHelper.insertResumeMatchInSharedPrefs(this, resumeMatch, this.match);
    }
}
