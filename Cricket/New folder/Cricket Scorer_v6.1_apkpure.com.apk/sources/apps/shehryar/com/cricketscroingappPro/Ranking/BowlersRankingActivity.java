package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.app.Activity;
import android.os.Bundle;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class BowlersRankingActivity extends Activity {
    ArrayList<Team> allTeams;
    private ArrayList<Long> allTeamsIds;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_batsmen_ranking);
        this.allTeams = (ArrayList) getIntent().getSerializableExtra("allTeams");
        this.allTeamsIds = (ArrayList) getIntent().getSerializableExtra("allTeamsIds");
        FragmentBowlersRanking fragmentBowlersRanking = new FragmentBowlersRanking();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("allTeams", this.allTeams);
        bundle2.putSerializable("allTeamsIds", this.allTeamsIds);
        fragmentBowlersRanking.setArguments(bundle2);
        getFragmentManager().beginTransaction().add(R.id.batsman_ranking_fragment_container, fragmentBowlersRanking).commit();
    }
}
