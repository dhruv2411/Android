package apps.shehryar.com.cricketscroingappPro.Partenership;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Batsman.Batsman;
import apps.shehryar.com.cricketscroingappPro.DBHelper;
import apps.shehryar.com.cricketscroingappPro.FallOfWickets;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;

public class PartenershipsActivity extends AppCompatActivity {
    ArrayList<Batsman> batsmen;
    ArrayList<FallOfWickets> fallOfWicketses;
    FrameLayout frameLayout;
    Match match;
    Team team;
    int totalScore;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_partenerships);
        try {
            getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.frameLayout = (FrameLayout) findViewById(R.id.fragment_container);
        this.batsmen = (ArrayList) getIntent().getSerializableExtra("batsmen");
        this.fallOfWicketses = (ArrayList) getIntent().getSerializableExtra("fow");
        this.totalScore = getIntent().getIntExtra(FirebaseAnalytics.Param.SCORE, 0);
        this.match = (Match) getIntent().getSerializableExtra(DBHelper.TABLE_MATCH);
        if (this.match == null) {
            this.match = new Match();
        }
        this.team = (Team) getIntent().getSerializableExtra(DBHelper.TABLE_TEAM);
        if (this.team == null) {
            this.team = new Team();
        }
        ArrayList<Partenership> arrayList = new ArrayList<>();
        try {
            arrayList = PartenershipHelper.getAllPartenerships2(arrayList, this.match, this.team);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (arrayList == null) {
                arrayList = new ArrayList<>();
            }
        }
        if (this.frameLayout != null) {
            FragmentPartenerships fragmentPartenerships = new FragmentPartenerships();
            Bundle bundle2 = new Bundle();
            bundle2.putSerializable("parts", arrayList);
            fragmentPartenerships.setArguments(bundle2);
            getSupportFragmentManager().beginTransaction().add((int) R.id.fragment_container, (Fragment) fragmentPartenerships).commit();
        }
    }
}
