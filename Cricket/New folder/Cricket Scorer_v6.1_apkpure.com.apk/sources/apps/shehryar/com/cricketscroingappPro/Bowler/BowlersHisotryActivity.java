package apps.shehryar.com.cricketscroingappPro.Bowler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Bowler.Dialog_Custom_ListView_Adapter;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Overs.Over;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class BowlersHisotryActivity extends AppCompatActivity {
    ArrayList<Bowler> bowlers_list;
    int code;
    DBHelper dbHelper;
    boolean fullact;
    Match match;
    ArrayList<Over> overs;
    boolean pre_bowling = false;
    Team team;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_bowlers_hisotry);
        try {
            FontProvider.setEuroStileFont(this, (TextView) findViewById(R.id.title));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getSupportActionBar().hide();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.pre_bowling = getIntent().getBooleanExtra("pre_bowling", false);
        this.fullact = getIntent().getBooleanExtra("fullact", false);
        this.overs = (ArrayList) getIntent().getSerializableExtra(DBHelper.TABLE_OVERS);
        this.match = (Match) getIntent().getSerializableExtra(DBHelper.TABLE_MATCH);
        if (this.match == null) {
            this.match = new Match();
        }
        if (!this.pre_bowling) {
            this.team = (Team) getIntent().getSerializableExtra(DBHelper.TABLE_TEAM);
            try {
                BowlerViewsUpdater.showBowlerRecyclerViewFragment(getSupportFragmentManager(), this.team.bowlers_list, (Dialog_Custom_ListView_Adapter.LinearLayoutClickLister) null, this.overs, this.match);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        } else {
            this.team = (Team) getIntent().getSerializableExtra(DBHelper.TABLE_TEAM);
            this.code = getIntent().getIntExtra("code", 0);
            this.dbHelper = new DBHelper(this);
            if (this.code == 99) {
                this.bowlers_list = this.dbHelper.getBowlers(this.match.getTeam_opp_id(), this.match.getMatchID());
            } else {
                this.bowlers_list = this.dbHelper.getBowlers(this.match.getTeam_Yours_id(), this.match.getMatchID());
            }
            Log.e("bowler score", this.bowlers_list.get(0).getName() + this.bowlers_list.get(0).getScore());
            try {
                BowlerViewsUpdater.showBowlerRecyclerViewFragment(getSupportFragmentManager(), this.bowlers_list, (Dialog_Custom_ListView_Adapter.LinearLayoutClickLister) null, this.overs, this.match);
            } catch (Exception e4) {
                e4.printStackTrace();
            }
        }
    }
}
