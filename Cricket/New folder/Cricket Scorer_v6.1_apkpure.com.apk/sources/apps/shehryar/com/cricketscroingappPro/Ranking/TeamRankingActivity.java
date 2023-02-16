package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.app.Activity;
import android.os.Bundle;
import apps.shehryar.com.cricketscroingapp.R;
import java.util.ArrayList;

public class TeamRankingActivity extends Activity {
    private ArrayList<Long> allTeamsIds;
    int matchType;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_team_ranking);
        this.allTeamsIds = (ArrayList) getIntent().getSerializableExtra("allTeamsIds");
        this.matchType = getIntent().getIntExtra("matchType", 1);
        FragmentTeamRanking fragmentTeamRanking = new FragmentTeamRanking();
        Bundle bundle2 = new Bundle();
        bundle2.putSerializable("allTeamsIds", this.allTeamsIds);
        bundle2.putInt("matchType", this.matchType);
        fragmentTeamRanking.setArguments(bundle2);
        getFragmentManager().beginTransaction().add(R.id.batsman_ranking_fragment_container, fragmentTeamRanking).commit();
    }
}
