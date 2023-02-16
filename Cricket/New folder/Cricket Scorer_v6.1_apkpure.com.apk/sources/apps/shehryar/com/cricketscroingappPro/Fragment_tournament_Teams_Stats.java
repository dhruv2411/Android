package apps.shehryar.com.cricketscroingappPro;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import apps.shehryar.com.cricketscroingapp.R;
import apps.shehryar.com.cricketscroingappPro.Match.Match;
import apps.shehryar.com.cricketscroingappPro.Team.Team;
import java.util.ArrayList;

public class Fragment_tournament_Teams_Stats extends Fragment {
    public static final String ALL_TEAM_STATS = "ALL_TEAM_STATS";
    ArrayList<Team> allTeamsStats;
    Context context;
    DBHelper dbHelper;
    ArrayList<Match> matches;
    ArrayList<String> player_names;
    ListView teamList;
    String team_name;

    public static Fragment_tournament_Teams_Stats newInstance(ArrayList<Team> arrayList) {
        Fragment_tournament_Teams_Stats fragment_tournament_Teams_Stats = new Fragment_tournament_Teams_Stats();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ALL_TEAM_STATS, arrayList);
        fragment_tournament_Teams_Stats.setArguments(bundle);
        return fragment_tournament_Teams_Stats;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.allTeamsStats = (ArrayList) getArguments().getSerializable(ALL_TEAM_STATS);
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.all_teams_stats_layout, viewGroup, false);
        this.teamList = (ListView) inflate.findViewById(R.id.batsmenlistview);
        try {
            this.teamList.setAdapter(new Custom_all_teams_stats_adapter(getActivity(), R.layout.all_team_stats_adapter_layout, this.allTeamsStats));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inflate;
    }
}
