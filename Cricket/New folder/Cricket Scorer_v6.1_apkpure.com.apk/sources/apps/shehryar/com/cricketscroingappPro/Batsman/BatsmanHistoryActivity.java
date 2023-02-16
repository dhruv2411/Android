package apps.shehryar.com.cricketscroingappPro.Batsman;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.AlertDialogBuilder;
import apps.shehryar.com.cricketscroingappPro.Utility_Classes.FontProvider;
import java.util.ArrayList;

public class BatsmanHistoryActivity extends AppCompatActivity {
    ListView batsmanlistview;
    ArrayList<Batsman> batsmans;
    int code;
    DBHelper dbHelper;
    Match match;
    boolean previousbat = false;
    Team team;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_batsman_history);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FontProvider.setEuroStileFont(this, (TextView) findViewById(R.id.title));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.dbHelper = new DBHelper(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        AlertDialogBuilder.showTipDialog2(this);
        this.match = (Match) extras.getSerializable(DBHelper.TABLE_MATCH);
        this.previousbat = extras.getBoolean("previousbat");
        this.batsmans = this.match.getBattingTeam().getBatsmans_list();
        if (!this.previousbat) {
            this.batsmans = removeAllOutFromList(this.batsmans);
        }
        FragmentBatsmanHistoryRecyclerView fragmentBatsmanHistoryRecyclerView = new FragmentBatsmanHistoryRecyclerView();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable(DBHelper.TABLE_MATCH, this.match);
        bundle2.putSerializable("batsmen", this.batsmans);
        fragmentBatsmanHistoryRecyclerView.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add((int) R.id.batsman_recycler_view_fragment, (Fragment) fragmentBatsmanHistoryRecyclerView).commit();
    }

    /* access modifiers changed from: package-private */
    public ArrayList<Batsman> removeAllOutFromList(ArrayList<Batsman> arrayList) {
        if (arrayList.get(arrayList.size() - 1).getName().equals("All Out")) {
            arrayList.remove(arrayList.size() - 1);
        }
        return arrayList;
    }
}
