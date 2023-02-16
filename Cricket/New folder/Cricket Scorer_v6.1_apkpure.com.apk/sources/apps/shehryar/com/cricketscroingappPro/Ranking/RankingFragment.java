package apps.shehryar.com.cricketscroingappPro.Ranking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class RankingFragment extends Fragment implements View.OnClickListener {
    ArrayList<Long> allTeamIds;
    ArrayList<Team> allTeams;
    Button bBatsmenRankings;
    Button bBowlerRankings;
    Button bTeamRankings;
    Intent intent;
    int matchType;

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ranking_fragment, viewGroup, false);
        this.bBatsmenRankings = (Button) inflate.findViewById(R.id.b_batsmen_ranking);
        this.bBowlerRankings = (Button) inflate.findViewById(R.id.b_bowlers_ranking);
        this.bTeamRankings = (Button) inflate.findViewById(R.id.b_team_rankings);
        this.bBatsmenRankings.setOnClickListener(this);
        this.bBowlerRankings.setOnClickListener(this);
        this.bTeamRankings.setOnClickListener(this);
        this.allTeams = (ArrayList) getArguments().getSerializable("allTeams");
        this.allTeamIds = (ArrayList) getArguments().getSerializable("allTeamsIds");
        this.matchType = getArguments().getInt("matchType");
        return inflate;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b_team_rankings /*2131755680*/:
                this.intent = new Intent(getActivity(), TeamRankingActivity.class);
                this.intent.putExtra("allTeamsIds", this.allTeamIds);
                this.intent.putExtra("matchType", this.matchType);
                startActivity(this.intent);
                return;
            case R.id.b_batsmen_ranking /*2131755681*/:
                this.intent = new Intent(getActivity(), BatsmenRankingActivity.class);
                this.intent.putExtra("allTeams", this.allTeams);
                this.intent.putExtra("allTeamsIds", this.allTeamIds);
                startActivity(this.intent);
                return;
            case R.id.b_bowlers_ranking /*2131755682*/:
                this.intent = new Intent(getActivity(), BowlersRankingActivity.class);
                this.intent.putExtra("allTeams", this.allTeams);
                this.intent.putExtra("allTeamsIds", this.allTeamIds);
                startActivity(this.intent);
                return;
            default:
                return;
        }
    }
}
